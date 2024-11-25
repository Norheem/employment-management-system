package com.nohim.employment.management.system.payload.request;


import com.nohim.employment.management.system.entity.enums.LeaveStatus;
import com.nohim.employment.management.system.entity.enums.ReasonsForLeave;
import com.nohim.employment.management.system.entity.enums.SalaryStatus;
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
public class SalaryRequest {

    private Long employeeId;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate monthOfPayment;

    private Double amount;

    @Enumerated(EnumType.STRING)
    private SalaryStatus salaryStatus;
}
