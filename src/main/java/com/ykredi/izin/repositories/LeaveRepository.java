package com.ykredi.izin.repositories;

import com.ykredi.izin.objects.LeaveState;
import com.ykredi.izin.objects.entities.Leave;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface LeaveRepository extends CrudRepository<Leave, Long> {
    Iterable<Leave> findAllByEmployeeId(UUID employeeId);

    Iterable<Leave> findAllByEmployeeIdAndStateIn(UUID employeeId, List<LeaveState> state);
}
