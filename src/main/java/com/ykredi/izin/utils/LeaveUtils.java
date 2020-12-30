package com.ykredi.izin.utils;

import com.ykredi.izin.objects.LeaveRequest;
import com.ykredi.izin.objects.entities.Employee;
import com.ykredi.izin.objects.entities.Leave;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.StreamSupport;

public class LeaveUtils {

    public static boolean isEmployeeAllowedToUseLeave(Employee employee, Iterable<Leave> usedLeaves, LeaveRequest leaveRequest) {
        switch (leaveRequest.getType()) {
            case ANNUAL:
                int workingYears = (int) ChronoUnit.YEARS.between(employee.getStartingDate(), LocalDate.now());
                int leaveCount = calculateLeaveCount(workingYears);
                int totalUsedLeaveDays = StreamSupport.stream(usedLeaves.spliterator(), false).mapToInt(Leave::getNumberOfDays).sum();
                // if employee is in first year, annual leave can be used up to 5 days.
                if (workingYears == 0 && totalUsedLeaveDays + leaveRequest.getNumberOfDays() <= 5) {
                    return true;
                }
                // if not first year, check if total used leaves + leave request is not bigger than it can be used by the employee
                return leaveRequest.getNumberOfDays() + totalUsedLeaveDays <= leaveCount;
            default:
                // do nothing
                break;

        }
        return false;
    }

    private static int calculateLeaveCount(int workingYears) {
        int leaveRight;
        if (workingYears == 0) {
            return 0;
        } else if (workingYears > 0 && workingYears <= 5) {
            leaveRight = 15 + calculateLeaveCount(workingYears - 1);
        } else if (workingYears > 5 && workingYears <= 10) {
            leaveRight = 18 + calculateLeaveCount(workingYears - 1);
        } else {
            leaveRight = 24 + calculateLeaveCount(workingYears - 1);
        }
        return leaveRight;
    }

    public static LocalDate addDaysSkippingWeekendsAndPublicHolidays(LocalDate date, int days, List<LocalDate> holidays) {
        LocalDate result = date;
        int addedDays = 1;
        while (addedDays < days) {
            result = result.plusDays(1);
            LocalDate finalResult = result;
            if (!(result.getDayOfWeek() == DayOfWeek.SATURDAY || result.getDayOfWeek() == DayOfWeek.SUNDAY) || (holidays != null && holidays.stream().anyMatch(finalResult::isEqual))) {
                addedDays++;
            }
        }
        return result;
    }

    public static boolean isLeavesDatesOverlapping(Leave leave, LeaveRequest leaveRequest) {
        return isTwoDatesOverlapping(leave.getStartingDate(), leave.getEndingDate(), leaveRequest.getStartingDate(), addDaysSkippingWeekendsAndPublicHolidays(leaveRequest.getStartingDate(), leave.getNumberOfDays(), null));
    }

    private static boolean isTwoDatesOverlapping(LocalDate dateOneStarts, LocalDate dateOneEnds, LocalDate dateTwoStarts, LocalDate dateTwoEnds) {
        return !dateOneEnds.isBefore(dateTwoStarts) && !dateOneStarts.isAfter(dateTwoEnds);
    }

    public static void main(String[] args) {
        LocalDate dateOneStarts = LocalDate.now();
        LocalDate dateOneEnds = LocalDate.now().plusDays(2);
        LocalDate dateTwoStarts = LocalDate.now().plusDays(3);
        LocalDate dateTwoEnds = LocalDate.now().plusDays(5);
        System.out.println(isTwoDatesOverlapping(dateOneStarts, dateOneEnds, dateTwoStarts, dateTwoEnds));
    }
}
