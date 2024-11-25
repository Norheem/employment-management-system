package com.nohim.employment.management.system.service.impl;

import com.nohim.employment.management.system.entity.Employee;
import com.nohim.employment.management.system.entity.Leave;
import com.nohim.employment.management.system.entity.enums.LeaveStatus;
import com.nohim.employment.management.system.payload.request.LeaveRequest;
import com.nohim.employment.management.system.payload.response.ApiResponse;
import com.nohim.employment.management.system.payload.response.LeaveResponse;
import com.nohim.employment.management.system.repository.EmployeeRepository;
import com.nohim.employment.management.system.repository.LeaveRepository;
import com.nohim.employment.management.system.service.LeaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.nohim.employment.management.system.entity.enums.LeaveStatus.PENDING;

@Service
@RequiredArgsConstructor
public class LeaveServiceImpl implements LeaveService {

    private final LeaveRepository leaveRepository;
    private final EmployeeRepository employeeRepository;


    @Override
    public ResponseEntity<ApiResponse<LeaveResponse>> applyForLeave(Long employeeId, LeaveRequest leaveRequest) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        Leave newLeave = Leave.builder()
                .employee(employee)
                .status(PENDING)
                .reasonsForLeave(leaveRequest.getReasonsForLeave())
                .startDate(leaveRequest.getStartDate())
                .endDate(leaveRequest.getEndDate())
                .build();

        leaveRepository.save(newLeave);

        LeaveResponse response = LeaveResponse.builder()
                .employeeId(employee.getId())
                .employeeName(employee.getFirstName() + " " + employee.getLastName())
                .status(newLeave.getStatus())
                .startDate(newLeave.getStartDate())
                .endDate(newLeave.getEndDate())
                .reasonsForLeave(newLeave.getReasonsForLeave())
                .build();


        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>("Leave request submitted successfully", response));

    }

    @Override
    public ResponseEntity<ApiResponse<LeaveResponse>> updateLeaveStatus(Long id, LeaveStatus status) {
        Leave leave = leaveRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Leave not found"));

        leave.setStatus(status);
        Leave updatedLeave = leaveRepository.save(leave);
        LeaveResponse response = LeaveResponse
                .builder()
                .startDate(updatedLeave.getStartDate())
                .endDate(updatedLeave.getEndDate())
                .reasonsForLeave(updatedLeave.getReasonsForLeave())
                .status(updatedLeave.getStatus())
                .build();
        return ResponseEntity.ok(new ApiResponse<>("Leave status updated successfully", response));
    }


    @Override
    public ResponseEntity<ApiResponse<List<LeaveResponse>>> getLeaveByEmployee(Long employeeId) {
        List<Leave> leaves = leaveRepository.findAllByEmployeeId(employeeId);
        List<LeaveResponse> responses = leaves.stream().map(leave -> LeaveResponse.builder()
                .startDate(leave.getStartDate())
                .endDate(leave.getEndDate())
                .reasonsForLeave(leave.getReasonsForLeave())
                .status(leave.getStatus())
                .employeeName(leave.getEmployee().getFirstName() + " " + leave.getEmployee().getLastName())
                .build()).toList();
        return ResponseEntity.ok(new ApiResponse<>(" Leave records retrieved", responses));
    }

    @Override
    public ResponseEntity<ApiResponse<List<LeaveResponse>>> getAllLeaves() {
        List<Leave> leaveLists = leaveRepository.findAll();

        List<LeaveResponse> responses = leaveLists.stream().map(leave -> LeaveResponse.builder()
                .employeeId(leave.getEmployee().getId())
                .startDate(leave.getStartDate())
                .endDate(leave.getEndDate())
                .reasonsForLeave(leave.getReasonsForLeave())
                .status(leave.getStatus())
                .employeeName(leave.getEmployee().getFirstName() + " " + leave.getEmployee().getLastName())
                .build()).toList();

        return ResponseEntity.ok(new ApiResponse<>("Leaves retrieved for all employee successfully", responses));
    }
}
