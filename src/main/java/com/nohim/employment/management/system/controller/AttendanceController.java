package com.nohim.employment.management.system.controller;

import com.nohim.employment.management.system.payload.request.AttendanceRequest;
import com.nohim.employment.management.system.payload.response.ApiResponse;
import com.nohim.employment.management.system.payload.response.AttendanceResponse;
import com.nohim.employment.management.system.payload.response.AttendanceSummaryResponse;
import com.nohim.employment.management.system.service.AttendanceService;
import com.nohim.employment.management.system.service.AttendanceSummaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;



@RestController
@RequestMapping("/api/v1/attendance")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;

    @Autowired
    private AttendanceSummaryService attendanceSummaryService;


    @PostMapping("/mark")
    public ResponseEntity<ApiResponse<String>> markAttendance(@RequestBody AttendanceRequest attendanceRequest) {
        return attendanceService.markAttendance(
                attendanceRequest.getEmployeeId(),
                attendanceRequest.getStatus(),
                attendanceRequest.getDate(),
                attendanceRequest.getReasons()
        );
    }



    @GetMapping("/employee/{id}")
    public ResponseEntity<ApiResponse<List<AttendanceResponse>>> getAttendanceByEmployee(@PathVariable Long id) {
        return attendanceService.getAttendanceByEmployee(id);
    }


    @GetMapping("/attendance/summary")
    public ResponseEntity<ApiResponse<AttendanceSummaryResponse>> getAttendanceSummary(
            @RequestParam Long employeeId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        return attendanceSummaryService.getAttendanceSummary(employeeId, startDate, endDate);
    }

    @GetMapping("/employee/details")
    public ResponseEntity<ApiResponse<List<AttendanceResponse>>> getAllAttendance(){
        return attendanceService.getAllAttendance();
    }
}
