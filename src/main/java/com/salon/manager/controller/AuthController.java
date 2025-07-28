package com.salon.manager.controller;

import com.salon.manager.config.JwtAuthenticationFilter;
import com.salon.manager.dto.requests.LoginRequest;
import com.salon.manager.dto.responses.LoginResponse;
import com.salon.manager.security.SecurityConfig;
import com.salon.manager.service.CustomUserDetails;
import com.salon.manager.service.CustomUserDetailsService;
import com.salon.manager.utils.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final CustomUserDetailsService userDetailsService;
    private final SecurityConfig config;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.email(),
                            request.password()
                    )
            );

            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            // Extract user role
            String role = userDetails.getAuthorities().stream()
                    .findFirst()
                    .map(GrantedAuthority::getAuthority)
                    .orElse("");

            String token = jwtTokenUtil.generateToken(userDetails);
            long expiresIn = jwtTokenUtil.getExpirationTime();

            LoginResponse response = new LoginResponse(userDetails.getId(), request.email(), token, "Bearer", expiresIn, role);

            return ResponseEntity.ok(response);

        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).build();
        }
    }
}