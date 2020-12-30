package com.ykredi.izin.objects;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LeaveRequest {
    private UUID employeeId;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate startingDate;
    private int numberOfDays;
    private LeaveType type;
}
