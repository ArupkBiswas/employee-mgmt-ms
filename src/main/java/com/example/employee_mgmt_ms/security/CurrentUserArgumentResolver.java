package com.example.employee_mgmt_ms.security;

import com.example.employee_mgmt_ms.model.securityDto.JwtUser;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
@RequiredArgsConstructor
public class CurrentUserArgumentResolver implements HandlerMethodArgumentResolver {

    private final JwtUtil jwtUtil;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterAnnotation(CurrentUser.class) != null
                && parameter.getParameterType().equals(JwtUser.class);
    }

    @Override
    public Object resolveArgument (MethodParameter parameter,
                                   ModelAndViewContainer mavContainer,
                                   NativeWebRequest webRequest,
                                   WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            Claims claims = jwtUtil.extractAllClaims(token);
            // userId claim may be stored as Number; convert safely
            Integer userId = null;
            Object uid = claims.get("userId");
            if (uid instanceof Number) {
                userId = ((Number) uid).intValue();
            } else if (uid instanceof String) {
                try { userId = Integer.valueOf((String) uid); } catch (NumberFormatException ignored) {}
            }
            String username = claims.getSubject();
            String role = claims.get("role", String.class);
            return new JwtUser(userId, username, role);
        }
        return null;
    }

}
