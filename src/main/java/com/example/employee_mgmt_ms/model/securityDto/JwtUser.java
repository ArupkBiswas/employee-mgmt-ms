package com.example.employee_mgmt_ms.model.securityDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtUser {
    private Integer userId;
    private String username;
    private String role;
}
