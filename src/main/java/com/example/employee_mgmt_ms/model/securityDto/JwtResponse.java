package com.example.employee_mgmt_ms.model.securityDto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class JwtResponse {
    private String token;
    @Builder.Default
    private String tokenType = "Bearer";
}
