package com.ykredi.izin.repositories;

import com.ykredi.izin.objects.entities.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface EmployeeRepository extends CrudRepository<Employee, UUID> {
}
