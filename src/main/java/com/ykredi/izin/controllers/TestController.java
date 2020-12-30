package com.ykredi.izin.controllers;

import com.ykredi.izin.objects.LeaveRequest;
import com.ykredi.izin.objects.entities.Employee;
import com.ykredi.izin.objects.entities.Leave;
import com.ykredi.izin.services.EmployeeService;
import com.ykredi.izin.services.LeaveService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@Validated
public class TestController {

    final private EmployeeService employeeService;
    final private LeaveService leaveService;

    public TestController(EmployeeService employeeService, LeaveService leaveService) {
        this.employeeService = employeeService;
        this.leaveService = leaveService;
    }

    @GetMapping(value = "/")
    public Iterable<Employee> getEmployees() {
        return employeeService.getEmployees();
    }

    @GetMapping(value = "/employee/{employeeId}/leaves")
    public Iterable<Leave> getEmployeeLeaves(@PathVariable UUID employeeId) {
        return leaveService.getEmployeeLeaves(employeeId);
    }

    @PostMapping(value = "/annual-leave-request")
    public boolean annualLeaveRequest(LeaveRequest leaveRequest) {
        return leaveService.newLeave(leaveRequest);
    }
}
