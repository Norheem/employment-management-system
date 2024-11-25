package com.nohim.employment.management.system.payload.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequest {

    private Long employeeId;

    private String firstName;

    private String lastName;

    private String email;

    private String password;
}
