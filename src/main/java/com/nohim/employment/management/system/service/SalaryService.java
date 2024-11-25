package com.nohim.employment.management.system.service;

import com.nohim.employment.management.system.entity.Salary;
import com.nohim.employment.management.system.entity.enums.SalaryStatus;
import com.nohim.employment.management.system.payload.response.ApiResponse;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

public interface SalaryService {

    ResponseEntity<ApiResponse<String>> addSalary(Long employeeId, LocalDate monthOfPayment, Double amount, SalaryStatus salaryStatus);

    ResponseEntity<ApiResponse<List<Salary>>> getSalaryByEmployee(Long employeeId);
}
