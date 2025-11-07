package com.example.employee_mgmt_ms.model.securityDto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JwtResponse {
    private String token;
    private String tokenType = "Bearer";

    public JwtResponse(String token) {
        this.token = token;
    }

}
