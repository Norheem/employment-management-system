package com.nohim.employment.management.system.service;

import com.nohim.employment.management.system.entity.Attendance;
import com.nohim.employment.management.system.entity.enums.ReasonsForAbsent;
import com.nohim.employment.management.system.entity.enums.StatusForAttendance;
import com.nohim.employment.management.system.payload.request.AttendanceRequest;
import com.nohim.employment.management.system.payload.response.ApiResponse;
import com.nohim.employment.management.system.payload.response.AttendanceResponse;
import com.nohim.employment.management.system.payload.response.AttendanceSummaryResponse;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceService {
    ResponseEntity<ApiResponse<String>> markAttendance(Long employeeId, StatusForAttendance status, LocalDate date, ReasonsForAbsent reasons);

    ResponseEntity<ApiResponse<List<AttendanceResponse>>> getAttendanceByEmployee(Long employeeId);
    ResponseEntity<ApiResponse<AttendanceSummaryResponse>> getAttendanceSummary( Long employeeId, LocalDate startDate, LocalDate endDate);
    ResponseEntity<ApiResponse<List<AttendanceResponse>>> getAllAttendance();
}
