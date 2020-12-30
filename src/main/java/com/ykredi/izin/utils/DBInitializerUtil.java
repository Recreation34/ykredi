package com.ykredi.izin.utils;

import com.ykredi.izin.objects.entities.Employee;
import com.ykredi.izin.repositories.EmployeeRepository;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.Arrays;

@Component
public class DBInitializerUtil {

    private final EmployeeRepository employeeRepository;

    public DBInitializerUtil(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @PostConstruct
    private void initDb() {
        if (employeeRepository.count() <= 0) {
            createEmployees();
        }
    }

    private void createEmployees() {

        Employee employee1 = Employee.builder().name("Fatih").surname("Parlayan").startingDate(LocalDate.now()).build();
        Employee employee2 = Employee.builder().name("Ali").surname("Veli").startingDate(LocalDate.now().minusYears(2)).build();
        Employee employee3 = Employee.builder().name("Ayşe").surname("Yılmaz").startingDate(LocalDate.now().minusYears(6)).build();
        Employee employee4 = Employee.builder().name("Fatma").surname("Boyraz").startingDate(LocalDate.now().minusYears(11)).build();

        employeeRepository.saveAll(Arrays.asList(employee1, employee2,
                employee3, employee4));

    }
}
