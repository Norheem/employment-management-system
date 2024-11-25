package com.nohim.employment.management.system.entity;


import com.nohim.employment.management.system.entity.enums.SalaryStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "salary_tbl")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Salary extends BaseClass{

    @Enumerated(EnumType.STRING)
    private SalaryStatus salaryStatus;

    private Double amount;

    private LocalDate monthOfPayment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;
}
