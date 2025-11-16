package com.example.employee_mgmt_ms.service;


import com.example.employee_mgmt_ms.model.dto.EmployeeDataEntity;
import com.example.employee_mgmt_ms.model.securityDto.JwtResponse;
import com.example.employee_mgmt_ms.model.securityDto.LoginRequest;
import com.example.employee_mgmt_ms.repository.dao.EmployeeDataRepository;
import com.example.employee_mgmt_ms.security.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final EmployeeDataRepository employeeDataRepository;

    public AuthService(AuthenticationManager authenticationManager,
                       JwtUtil jwtUtil,
                       EmployeeDataRepository employeeDataRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.employeeDataRepository = employeeDataRepository;
    }

    public JwtResponse authenticateUser(LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(), loginRequest.getPassword()
                    )
            );

            EmployeeDataEntity user = employeeDataRepository.findByUsername(loginRequest.getUsername())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            String token = jwtUtil.generateToken(
                    user.getUsername(),
                    Long.valueOf(user.getId()),
                    user.getRole()
            );

            return JwtResponse.builder().token(token).build();
        } catch (AuthenticationException ex) {
            throw new RuntimeException("Invalid username or password");
        }
    }
}
