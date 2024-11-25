package com.nohim.employment.management.system.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDetailsResponse {

    private Long employeeId;
    private String name;
    private String email;
    private List<AttendanceResponse> attendanceHistory;
    private List<SalaryResponse> salaryHistory;
    private List<LeaveResponse> leaveHistory;
}
