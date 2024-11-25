package com.nohim.employment.management.system.controller;


import com.nohim.employment.management.system.entity.Salary;
import com.nohim.employment.management.system.payload.request.SalaryRequest;
import com.nohim.employment.management.system.payload.response.ApiResponse;
import com.nohim.employment.management.system.service.SalaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/salary")
@RequiredArgsConstructor
public class SalaryController {

    private final SalaryService salaryService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<String>> addSalary(
            @RequestBody SalaryRequest salaryRequest) {
        return salaryService.addSalary(
                salaryRequest.getEmployeeId(),
                salaryRequest.getMonthOfPayment(),
                salaryRequest.getAmount(),
                salaryRequest.getSalaryStatus()
        );
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<ApiResponse<List<Salary>>> getSalaryByEmployee(@PathVariable Long id) {
        return salaryService.getSalaryByEmployee(id);
    }
}
