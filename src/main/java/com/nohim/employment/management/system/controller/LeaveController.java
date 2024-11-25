package com.nohim.employment.management.system.controller;


import com.nohim.employment.management.system.entity.Leave;
import com.nohim.employment.management.system.entity.enums.LeaveStatus;
import com.nohim.employment.management.system.payload.request.LeaveRequest;
import com.nohim.employment.management.system.payload.response.ApiResponse;
import com.nohim.employment.management.system.payload.response.LeaveResponse;
import com.nohim.employment.management.system.service.LeaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/leave")
@RequiredArgsConstructor
public class LeaveController {

    private final LeaveService leaveService;

    @PostMapping("/apply/{employeeId}")
    public ResponseEntity<ApiResponse<LeaveResponse>> applyForLeave(
            @PathVariable Long employeeId,
            @RequestBody LeaveRequest leaveRequest) {
        return leaveService.applyForLeave(employeeId, leaveRequest);
    }

    @PutMapping("status/{leaveId}")
    public ResponseEntity<ApiResponse<LeaveResponse>> updateLeaveStatus(
            @PathVariable Long leaveId,
            @RequestParam LeaveStatus status) {
        return leaveService.updateLeaveStatus(leaveId, status);
    }


    @GetMapping("/employee/{id}")
    public ResponseEntity<ApiResponse<List<LeaveResponse>>> getLeaveByEmployee(@PathVariable Long id) {
        return leaveService.getLeaveByEmployee(id);
    }

    @GetMapping("/employees/details")
    public ResponseEntity<ApiResponse<List<LeaveResponse>>> getAllLeaves() {
        return leaveService.getAllLeaves();
    }
}
