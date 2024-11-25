package com.nohim.employment.management.system.payload.request;

import com.nohim.employment.management.system.entity.enums.ReasonsForAbsent;
import com.nohim.employment.management.system.entity.enums.StatusForAttendance;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceRequest {

    private Long employeeId;

    @Enumerated(EnumType.STRING)
    private StatusForAttendance status;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private ReasonsForAbsent reasons;
}
