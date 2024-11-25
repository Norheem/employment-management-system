package com.nohim.employment.management.system.entity;


import com.nohim.employment.management.system.entity.enums.Roles;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "employee_tbl")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employee extends BaseClass{

    private String firstName;

    private String lastName;

    private String email;

    private Double salary;

    private String password;

    @Enumerated(EnumType.STRING)
    private Roles roles;

    private LocalDate join_date;


    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<Attendance> attendances;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<Leave> leaves;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<Salary> salaries;

}
