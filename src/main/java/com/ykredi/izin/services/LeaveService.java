package com.ykredi.izin.services;

import com.ykredi.izin.exception.ContentNotFoundException;
import com.ykredi.izin.exception.ErrorCode;
import com.ykredi.izin.exception.InputNotValidException;
import com.ykredi.izin.objects.LeaveRequest;
import com.ykredi.izin.objects.LeaveState;
import com.ykredi.izin.objects.entities.Employee;
import com.ykredi.izin.objects.entities.Leave;
import com.ykredi.izin.repositories.LeaveRepository;
import com.ykredi.izin.utils.LeaveUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.UUID;

@Service
public class LeaveService {

    final private LeaveRepository leaveRepository;
    final private EmployeeService employeeService;

    public LeaveService(LeaveRepository leaveRepository, EmployeeService employeeService) {
        this.leaveRepository = leaveRepository;
        this.employeeService = employeeService;
    }

    public boolean newLeave(LeaveRequest leaveRequest) {
        if (leaveRequest.getNumberOfDays() < 1)
            throw new InputNotValidException(ErrorCode.LEAVE_DAY_MUST_BE_BIGGER_THAN_ZERO);
        if (leaveRequest.getStartingDate() == null || leaveRequest.getStartingDate().isBefore(LocalDate.now()))
            throw new InputNotValidException(ErrorCode.LEAVE_STARTING_DATE_CAN_NOT_BE_A_PASSED_DAY);

        Employee employee = employeeService.getEmployee(leaveRequest.getEmployeeId()).orElseThrow(() -> new ContentNotFoundException("Employee"));
        Iterable<Leave> usedEmployeeLeaves = leaveRepository.findAllByEmployeeIdAndStateIn(leaveRequest.getEmployeeId(), Arrays.asList(LeaveState.WAITING_FOR_APPROVAL, LeaveState.APPROVED));

        for (Leave usedLeave : usedEmployeeLeaves) {
            if(LeaveUtils.isLeavesDatesOverlapping(usedLeave, leaveRequest))
                throw new InputNotValidException(ErrorCode.ALREADY_HAVE_LEAVE_IN_THIS_DATE);
        }

        boolean hasRight = LeaveUtils.isEmployeeAllowedToUseLeave(employee, usedEmployeeLeaves, leaveRequest);
        if (hasRight) {
            leaveRepository.save(Leave.builder()
                    .employeeId(leaveRequest.getEmployeeId())
                    .startingDate(leaveRequest.getStartingDate())
                    .endingDate(LeaveUtils.addDaysSkippingWeekendsAndPublicHolidays(leaveRequest.getStartingDate(), leaveRequest.getNumberOfDays(), null))
                    .state(LeaveState.WAITING_FOR_APPROVAL)
                    .type(leaveRequest.getType())
                    .numberOfDays(leaveRequest.getNumberOfDays())
                    .build());
            return true;
        } else {
            throw new InputNotValidException(ErrorCode.HAS_NO_LEAVE_RIGHT);
        }
    }

    public Iterable<Leave> getEmployeeLeaves(UUID employeeId) {
        return leaveRepository.findAllByEmployeeId(employeeId);
    }
}
