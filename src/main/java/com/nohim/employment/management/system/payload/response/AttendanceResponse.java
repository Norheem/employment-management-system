package com.nohim.employment.management.system.payload.response;

import com.nohim.employment.management.system.entity.enums.ReasonsForAbsent;
import com.nohim.employment.management.system.entity.enums.StatusForAttendance;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceResponse {

    private Long employeeId;

    private String employeeName;

    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private StatusForAttendance status;

    @Enumerated(EnumType.STRING)
    private ReasonsForAbsent reasons;
}
