package com.nohim.employment.management.system.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponse {

    private Long employeeId;

    private String firstName;

    private String lastName;

    private String email;
}
