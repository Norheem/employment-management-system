package com.nohim.employment.management.system.service.impl;

import com.nohim.employment.management.system.entity.Attendance;
import com.nohim.employment.management.system.entity.Employee;
import com.nohim.employment.management.system.entity.Salary;
import com.nohim.employment.management.system.entity.enums.Absent;
import com.nohim.employment.management.system.entity.enums.Present;
import com.nohim.employment.management.system.entity.enums.ReasonsForAbsent;
import com.nohim.employment.management.system.entity.enums.StatusForAttendance;
import com.nohim.employment.management.system.payload.response.ApiResponse;
import com.nohim.employment.management.system.payload.response.AttendanceResponse;
import com.nohim.employment.management.system.payload.response.AttendanceSummaryResponse;
import com.nohim.employment.management.system.repository.AttendanceRepository;
import com.nohim.employment.management.system.repository.EmployeeRepository;
import com.nohim.employment.management.system.repository.SalaryRepository;
import com.nohim.employment.management.system.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final EmployeeRepository employeeRepository;
    private final SalaryRepository salaryRepository;


    @Override
    public ResponseEntity<ApiResponse<String>> markAttendance(Long employeeId, StatusForAttendance status, LocalDate date, ReasonsForAbsent reasons) {
        if (!employeeRepository.existsById(employeeId)) {
            throw new RuntimeException("Employee not found");
        }

        if (attendanceRepository.existsByEmployeeIdAndDate(employeeId, date)) {
            throw new RuntimeException("Attendance already marked for this date");
        }

        // Fetch the employee entity
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        Attendance attendance = Attendance.builder()
                .employee(employeeRepository.findById(employeeId).orElseThrow())
                .date(date)
                .status(status)
                .reasons(reasons)
                .build();

        attendanceRepository.save(attendance);


        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>("Attendance marked successfully", null));
    }

    @Override
    public ResponseEntity<ApiResponse<List<AttendanceResponse>>> getAttendanceByEmployee(Long employeeId) {
        List<Attendance> attendances = attendanceRepository.findAllByEmployeeId(employeeId);
        List<AttendanceResponse> responses = attendances.stream().map(attendance -> AttendanceResponse.builder()
                .employeeId(attendance.getEmployee().getId())
                .date(attendance.getDate())
                .reasons(attendance.getReasons())
                .status(attendance.getStatus())
                .employeeName(attendance.getEmployee().getFirstName() + " " + attendance.getEmployee().getLastName())
                .build()).toList();
        return ResponseEntity.ok(new ApiResponse<>("Attendance records retrieved", responses));

    }


    @Override
    public ResponseEntity<ApiResponse<AttendanceSummaryResponse>> getAttendanceSummary(
            Long employeeId, LocalDate startDate, LocalDate endDate) {

        List<Attendance> attendanceList = attendanceRepository.findAllByEmployeeIdAndDateBetween(employeeId, startDate, endDate);

        int daysPresent = 0;
        int daysAbsent = 0;

        for (Attendance attendance : attendanceList) {
            if (attendance.getStatus() == StatusForAttendance.PRESENT) {
                daysPresent++;
            } else if (attendance.getStatus() == StatusForAttendance.ABSENT) {
                daysAbsent++;
            }
        }

        Salary salary = salaryRepository.findTopByEmployeeIdOrderByMonthOfPaymentDesc(employeeId);
        Double employeeSalary = (salary != null) ? salary.getAmount() : 0.0;


        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));


        Present present = (daysPresent > 0) ? Present.YES : Present.NO;
        Absent absent = (daysAbsent > 0) ? Absent.YES : Absent.NO;


        AttendanceSummaryResponse summaryResponse = AttendanceSummaryResponse.builder()
                .employeeId(employee.getId())
                .employeeName(employee.getFirstName() + " " + employee.getLastName())
                .present(present)
                .absent(absent)
                .daysPresent(daysPresent)
                .daysAbsent(daysAbsent)
                .salary(employeeSalary)
                .build();


        ApiResponse<AttendanceSummaryResponse> apiResponse = new ApiResponse<>("Attendance summary retrieved", summaryResponse);
        return ResponseEntity.ok(apiResponse);
    }

    @Override
    public ResponseEntity<ApiResponse<List<AttendanceResponse>>> getAllAttendance() {
        List<Attendance> attendanceLists = attendanceRepository.findAll();

        List<AttendanceResponse> responses = attendanceLists.stream().map(attendance -> AttendanceResponse.builder()
                .employeeId(attendance.getEmployee().getId())
                .employeeName(attendance.getEmployee().getFirstName() + " " + attendance.getEmployee().getLastName())
                .date(attendance.getDate())
                .status(attendance.getStatus())
                .reasons(attendance.getReasons())
                .build()).toList();

        return ResponseEntity.ok(new ApiResponse<>("Attendance records for all employee retrieved successfully", responses));
    }

}
