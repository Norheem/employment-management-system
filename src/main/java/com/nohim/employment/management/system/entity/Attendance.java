package com.nohim.employment.management.system.entity;

import com.nohim.employment.management.system.entity.enums.Absent;
import com.nohim.employment.management.system.entity.enums.Present;
import com.nohim.employment.management.system.entity.enums.ReasonsForAbsent;
import com.nohim.employment.management.system.entity.enums.StatusForAttendance;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@Entity
@Table(name = "attendance_tbl")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Attendance extends BaseClass{

    @Enumerated(EnumType.STRING)
    private StatusForAttendance status;

    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private ReasonsForAbsent reasons;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;
}
