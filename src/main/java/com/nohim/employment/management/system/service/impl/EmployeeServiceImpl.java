package com.nohim.employment.management.system.service.impl;

import com.nohim.employment.management.system.entity.Employee;
import com.nohim.employment.management.system.entity.enums.Roles;
import com.nohim.employment.management.system.payload.request.EmployeeRequest;
import com.nohim.employment.management.system.payload.response.*;
import com.nohim.employment.management.system.repository.AttendanceRepository;
import com.nohim.employment.management.system.repository.EmployeeRepository;
import com.nohim.employment.management.system.repository.LeaveRepository;
import com.nohim.employment.management.system.repository.SalaryRepository;
import com.nohim.employment.management.system.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.nohim.employment.management.system.entity.enums.Roles.USER;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    //private final EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private LeaveRepository leaveRepository;

    @Autowired
    private SalaryRepository salaryRepository;



    @Override
    public ResponseEntity<ApiResponse<EmployeeResponse>> registerEmployee(EmployeeRequest employeeRequest) {

        boolean isEmailPresent = employeeRepository.existsByEmail(employeeRequest.getEmail());

        if (isEmailPresent) {
            throw new RuntimeException("Email already exists");
        }

        Employee newEmployee = Employee.builder()
                .firstName(employeeRequest.getFirstName())
                .lastName(employeeRequest.getLastName())
                .email(employeeRequest.getEmail())
                .password(employeeRequest.getPassword())
                .roles(USER)
                .build();

        employeeRepository.save(newEmployee);

        EmployeeResponse response = EmployeeResponse.builder()
                .employeeId(employeeRequest.getEmployeeId())
                .firstName(employeeRequest.getFirstName())
                .lastName(employeeRequest.getLastName())
                .email(employeeRequest.getEmail())
                .build();

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>("Account created successfully", response));
    }

    public EmployeeDetailsResponse getEmployeeDetails(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        List<AttendanceResponse> attendanceHistory = attendanceRepository.findAttendanceByEmployeeId(employeeId)
                .stream()
                .map(attendance -> AttendanceResponse.builder()
                        .employeeName(attendance.getEmployee().getFirstName() + " " + attendance.getEmployee().getLastName())
                        .date(attendance.getDate())
                        .reasons(attendance.getReasons())
                        .status(attendance.getStatus())
                        .build()).toList();
        List<LeaveResponse> leaveHistory = (List<LeaveResponse>) leaveRepository.findLeaveByEmployeeId(employeeId)
                .stream()
                .map(leave -> new LeaveResponse(
                        leave.getEmployee().getId(),
                        leave.getEmployee().getFirstName() + " " + leave.getEmployee().getLastName(),
                        leave.getStartDate(),
                        leave.getEndDate(),
                        leave.getStatus(),
                        leave.getReasonsForLeave())
                )
                .collect(Collectors.toList());
        List<SalaryResponse> salaryHistory = salaryRepository.findSalaryByEmployeeId(employeeId)
                .stream()
                .map(salary -> new SalaryResponse(
                        salary.getEmployee().getId(),
                        salary.getMonthOfPayment(),
                        salary.getAmount(),
                        salary.getSalaryStatus()))
                .collect(Collectors.toList());

        return EmployeeDetailsResponse.builder()
                .employeeId(employee.getId())
                .name(employee.getFirstName() + " " + employee.getLastName())
                .email(employee.getEmail())
                .attendanceHistory(attendanceHistory)
                .salaryHistory(salaryHistory)
                .leaveHistory(leaveHistory)
                .build();
    }

}
