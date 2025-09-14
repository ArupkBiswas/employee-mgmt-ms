package com.example.employee_mgmt_ms.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

// This class is a placeholder for employee data request.
// It can be extended in the future to include fields and methods as needed.
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmployeeDataRequest {
    @JsonProperty(value = "id")
    @NotNull
    private Integer id;
    @JsonProperty(value = "firstName")
    private String firstName;
    @JsonProperty(value = "lastName")
    private String lastName;
    @JsonProperty(value = "email")
    private String email;
    @JsonProperty(value = "phoneNumber")
    private String phoneNumber;
    @JsonProperty(value = "joiningDate")
    private Date joiningDate;
    @JsonProperty(value = "jobId")
    private String jobId;
    @JsonProperty(value = "salary")
    private Integer salary;
    @JsonProperty(value = "commissionPct")
    private Integer commissionPct;
    @JsonProperty(value = "managerId")
    private Integer managerId;
    @JsonProperty(value = "departmentId")
    private Integer departmentId;
    // Additional fields can be added as needed
}
