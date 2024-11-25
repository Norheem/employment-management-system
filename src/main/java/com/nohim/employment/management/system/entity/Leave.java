package com.nohim.employment.management.system.entity;


import com.nohim.employment.management.system.entity.enums.LeaveStatus;
import com.nohim.employment.management.system.entity.enums.ReasonsForLeave;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "leave_tbl")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Leave extends BaseClass{

    @Enumerated(EnumType.STRING)
    private LeaveStatus status;

    @Enumerated(EnumType.STRING)
    private ReasonsForLeave reasonsForLeave;

    private LocalDate startDate;

    private LocalDate endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;
}
