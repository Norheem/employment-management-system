package com.nohim.employment.management.system.service.impl;

import com.nohim.employment.management.system.entity.Attendance;
import com.nohim.employment.management.system.entity.Employee;
import com.nohim.employment.management.system.entity.Salary;
import com.nohim.employment.management.system.entity.enums.Absent;
import com.nohim.employment.management.system.entity.enums.Present;
import com.nohim.employment.management.system.entity.enums.StatusForAttendance;
import com.nohim.employment.management.system.payload.response.ApiResponse;
import com.nohim.employment.management.system.payload.response.AttendanceSummaryResponse;
import com.nohim.employment.management.system.repository.AttendanceRepository;
import com.nohim.employment.management.system.repository.EmployeeRepository;
import com.nohim.employment.management.system.repository.SalaryRepository;
import com.nohim.employment.management.system.service.AttendanceSummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AttendanceSummaryServiceImpl implements AttendanceSummaryService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private SalaryRepository salaryRepository;

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


        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        Salary salary = salaryRepository.findTopByEmployeeIdOrderByMonthOfPaymentDesc(employeeId);
        Double employeeSalary = (salary != null) ? salary.getAmount() : 0.0;


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
}
