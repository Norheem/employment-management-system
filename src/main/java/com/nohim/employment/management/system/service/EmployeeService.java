package com.nohim.employment.management.system.service;

import com.nohim.employment.management.system.payload.request.EmployeeRequest;
import com.nohim.employment.management.system.payload.response.ApiResponse;
import com.nohim.employment.management.system.payload.response.EmployeeDetailsResponse;
import com.nohim.employment.management.system.payload.response.EmployeeResponse;
import org.springframework.http.ResponseEntity;

public interface EmployeeService {

    ResponseEntity<ApiResponse<EmployeeResponse>> registerEmployee(EmployeeRequest employeeRequest);

    EmployeeDetailsResponse getEmployeeDetails(Long employeeId);
}
