package com.nohim.employment.management.system.service.impl;

import com.nohim.employment.management.system.entity.Employee;
import com.nohim.employment.management.system.entity.Salary;
import com.nohim.employment.management.system.entity.enums.SalaryStatus;
import com.nohim.employment.management.system.payload.response.ApiResponse;
import com.nohim.employment.management.system.repository.EmployeeRepository;
import com.nohim.employment.management.system.repository.SalaryRepository;
import com.nohim.employment.management.system.service.SalaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
@RequiredArgsConstructor
public class SalaryServiceImpl implements SalaryService {

    private final SalaryRepository salaryRepository;
    private final EmployeeRepository employeeRepository;


    @Override
    public ResponseEntity<ApiResponse<String>> addSalary(Long employeeId, LocalDate monthOfPayment, Double amount, SalaryStatus salaryStatus) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        Salary salary = Salary.builder()
                .employee(employee)
                .monthOfPayment(monthOfPayment)
                .amount(amount)
                .salaryStatus(salaryStatus)
                .build();

        salaryRepository.save(salary);

        return ResponseEntity.ok(new ApiResponse<>("Salary added successfully", null));
    }


    @Override
    public ResponseEntity<ApiResponse<List<Salary>>> getSalaryByEmployee(Long employeeId) {
        List<Salary> salaries = salaryRepository.findAllByEmployeeId(employeeId);
        return ResponseEntity.ok(new ApiResponse<>("Salary records retrieved", salaries));
    }
}
