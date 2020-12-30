package com.ykredi.izin.objects.entities;

import com.ykredi.izin.objects.LeaveState;
import com.ykredi.izin.objects.LeaveType;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.UUID;

@Builder
@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Leave {
    @Id
    @GeneratedValue
    private long id;
    private UUID employeeId;
    private LocalDate startingDate;
    private LocalDate endingDate;
    private int numberOfDays;
    private LeaveType type;
    private LeaveState state;
}
