package com.example.employee_mgmt_ms.service;

import com.example.employee_mgmt_ms.model.dto.EmployeeDataEntity;
import com.example.employee_mgmt_ms.model.securityDto.UserPrincipal;
import com.example.employee_mgmt_ms.repository.dao.EmployeeDataRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Loads user details from DB. Adapt to your repository/entity.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final EmployeeDataRepository employeeDataRepository;

    public CustomUserDetailsService(EmployeeDataRepository employeeDataRepository) {
        this.employeeDataRepository = employeeDataRepository;
    }

    /**
     * Look up user by username (or email). Adjust as per your domain.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        EmployeeDataEntity user = employeeDataRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
        return UserPrincipal.create(user);
    }

}
