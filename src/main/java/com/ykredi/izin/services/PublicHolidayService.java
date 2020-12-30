package com.ykredi.izin.services;

import com.ykredi.izin.objects.entities.PublicHoliday;
import com.ykredi.izin.repositories.PublicHolidayRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PublicHolidayService {

    final private PublicHolidayRepository publicHolidayRepository;

    public PublicHolidayService(PublicHolidayRepository publicHolidayRepository) {
        this.publicHolidayRepository = publicHolidayRepository;
    }

    public void addNewHoliday(LocalDate date) {
        publicHolidayRepository.save(PublicHoliday.builder().date(date).build());
    }
}
