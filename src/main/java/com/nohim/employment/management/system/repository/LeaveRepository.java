package com.nohim.employment.management.system.repository;

import com.nohim.employment.management.system.entity.Employee;
import com.nohim.employment.management.system.entity.Leave;
import com.nohim.employment.management.system.payload.response.LeaveResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface LeaveRepository extends JpaRepository<Leave, Long> {
    List<Leave> findAllByEmployeeId(Long employeeId);

    List<Leave> findLeaveByEmployeeId(Long employeeId);
}
