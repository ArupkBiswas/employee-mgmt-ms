package com.example.employee_mgmt_ms.model.securityDto;

import com.example.employee_mgmt_ms.model.dto.EmployeeDataEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

/**
 * Simple UserDetails implementation. Extend to include roles/authorities.
 */
public class UserPrincipal implements UserDetails {

    private final String username;
    private final String password;
    private final boolean enabled;

    public UserPrincipal(String username, String password, boolean enabled) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
    }

    public static UserPrincipal create(EmployeeDataEntity entity) {
        // adapt: map roles/authorities if you have them
        return new UserPrincipal(entity.getUsername(), entity.getPassword(), entity.isEnabled());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getPassword() { return password; }

    @Override
    public String getUsername() { return username; }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return enabled; }
}
