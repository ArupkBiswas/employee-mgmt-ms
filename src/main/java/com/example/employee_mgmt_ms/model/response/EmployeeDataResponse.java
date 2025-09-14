package com.example.employee_mgmt_ms.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmployeeDataResponse {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Date joiningDate;
    private String jobId;
    private BigInteger salary;
    private Integer commissionPct;
    private Integer managerId;
    private Integer departmentId;

    // Additional fields can be added as needed
}
