package com.nohim.employment.management.system.service;

import com.nohim.employment.management.system.entity.Leave;
import com.nohim.employment.management.system.entity.enums.LeaveStatus;
import com.nohim.employment.management.system.entity.enums.ReasonsForLeave;
import com.nohim.employment.management.system.payload.request.LeaveRequest;
import com.nohim.employment.management.system.payload.response.ApiResponse;
import com.nohim.employment.management.system.payload.response.LeaveResponse;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

public interface LeaveService {

    ResponseEntity<ApiResponse<LeaveResponse>> applyForLeave(Long employeeId, LeaveRequest leaveRequest);
    ResponseEntity<ApiResponse<LeaveResponse>> updateLeaveStatus(Long id, LeaveStatus status);
    ResponseEntity<ApiResponse<List<LeaveResponse>>> getLeaveByEmployee(Long employeeId);
    ResponseEntity<ApiResponse<List<LeaveResponse>>> getAllLeaves();
}
