package com.nohim.employment.management.system.payload.response;


import com.nohim.employment.management.system.entity.enums.SalaryStatus;
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
public class SalaryResponse {

    private Long employeeId;

    private LocalDate monthOfPayment;

    private Double amount;

    @Enumerated(EnumType.STRING)
    private SalaryStatus salaryStatus;
}
