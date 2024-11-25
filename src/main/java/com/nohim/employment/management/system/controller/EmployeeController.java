package com.nohim.employment.management.system.controller;

import com.nohim.employment.management.system.payload.request.EmployeeRequest;
import com.nohim.employment.management.system.payload.response.ApiResponse;
import com.nohim.employment.management.system.payload.response.EmployeeDetailsResponse;
import com.nohim.employment.management.system.payload.response.EmployeeResponse;
import com.nohim.employment.management.system.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    /**
     * Endpoint to get the full details of an employee
     *
     * @param employeeRequest the request body containing the employee's details
     * @return EmployeeDetailsResponse - response containing employee details
     */

    @PostMapping("register")
    public ResponseEntity<ApiResponse<EmployeeResponse>> registerEmployee(
            @RequestBody EmployeeRequest employeeRequest) {

        return employeeService.registerEmployee(employeeRequest);
    }

        @GetMapping("/{employeeId}/details")
        public ResponseEntity<EmployeeDetailsResponse> getEmployeeDetails(@PathVariable Long employeeId) {

            EmployeeDetailsResponse employeeDetails = employeeService.getEmployeeDetails(employeeId);

            return ResponseEntity.ok(employeeDetails);
        }

}
