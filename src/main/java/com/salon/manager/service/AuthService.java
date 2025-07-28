package com.salon.manager.service;

import com.salon.manager.dto.requests.LoginRequest;
import com.salon.manager.dto.responses.LoginResponse;
import com.salon.manager.exception.AuthException;
import com.salon.manager.utils.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final CustomUserDetailsService userDetailsService;

    public LoginResponse authenticateUser(LoginRequest request) throws AuthException {
        try {
            // Authenticate credentials
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.email(),
                            request.password()
                    )
            );

            // Generate token
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            String token = jwtTokenUtil.generateToken(userDetails);

            // Extract role
            String role = userDetails.getAuthorities().stream()
                    .findFirst()
                    .map(GrantedAuthority::getAuthority)
                    .orElse("");

            return new LoginResponse(
                    userDetails.getId(),
                    request.email(),
                    token,
                    "Bearer",
                    jwtTokenUtil.getExpirationTime(),
                    role
            );

        } catch (AuthenticationException e) {
            throw new AuthException("Authentication failed", e);
        }
    }
}
