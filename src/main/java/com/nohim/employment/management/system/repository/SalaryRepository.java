package com.nohim.employment.management.system.repository;

import com.nohim.employment.management.system.entity.Employee;
import com.nohim.employment.management.system.entity.Salary;
import com.nohim.employment.management.system.payload.response.SalaryResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface SalaryRepository extends JpaRepository<Salary, Long> {

    List<Salary> findAllByEmployeeId(Long employeeId);
    boolean existsByEmployeeIdAndMonthOfPayment(Long employeeId, LocalDate monthOfPayment);

    Salary findTopByEmployeeIdOrderByMonthOfPaymentDesc(Long employeeId);

    List<Salary> findSalaryByEmployeeId(Long employeeId);
}
