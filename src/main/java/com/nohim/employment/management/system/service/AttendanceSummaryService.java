package com.nohim.employment.management.system.service;

import com.nohim.employment.management.system.payload.response.ApiResponse;
import com.nohim.employment.management.system.payload.response.AttendanceSummaryResponse;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

public interface AttendanceSummaryService {

    ResponseEntity<ApiResponse<AttendanceSummaryResponse>> getAttendanceSummary(Long employeeId, LocalDate startDate, LocalDate endDate);
}
