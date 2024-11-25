package com.nohim.employment.management.system.repository;

import com.nohim.employment.management.system.entity.Attendance;
import com.nohim.employment.management.system.entity.Employee;
import com.nohim.employment.management.system.payload.response.AttendanceResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    boolean existsByEmployeeIdAndDate(Long employeeId, LocalDate date);

    List<Attendance> findAllByEmployeeId(Long employeeId);

    List<Attendance> findAllByEmployeeIdAndDateBetween(Long employeeId, LocalDate startDate, LocalDate endDate);

    List<Attendance> findAttendanceByEmployeeId(@Param("employeeId") Long employeeId);
}
