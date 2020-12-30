package com.ykredi.izin.objects.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Builder
@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class PublicHoliday {
    @Id
    @GeneratedValue
    private long id;
    private LocalDate date;
}
