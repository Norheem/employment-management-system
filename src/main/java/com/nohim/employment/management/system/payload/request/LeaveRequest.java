package com.nohim.employment.management.system.payload.request;

import com.nohim.employment.management.system.entity.enums.LeaveStatus;
import com.nohim.employment.management.system.entity.enums.ReasonsForLeave;
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
public class LeaveRequest {

    private Long employeeId;

    @Enumerated(EnumType.STRING)
    private LeaveStatus status;

    @Enumerated(EnumType.STRING)
    private ReasonsForLeave reasonsForLeave;

    private LocalDate startDate;

    private LocalDate endDate;
}
