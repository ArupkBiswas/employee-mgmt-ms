package com.example.employee_mgmt_ms.model.securityDto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginRequest {
    // getters & setters
    @NotBlank
    private String username;

    @NotBlank
    private String password;

}
