package com.nohim.employment.management.system.payload.response;

import com.nohim.employment.management.system.entity.enums.Absent;
import com.nohim.employment.management.system.entity.enums.Present;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceSummaryResponse {

    private Long employeeId;

    private String employeeName;

    @Enumerated(EnumType.STRING)
    private Present present;

    @Enumerated(EnumType.STRING)
    private Absent absent;

    private int daysPresent;

    private int daysAbsent;

    private Double salary;
}
