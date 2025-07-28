package com.salon.manager.controller;

import com.salon.manager.dto.requests.LoginRequest;
import com.salon.manager.dto.responses.LoginResponse;
import com.salon.manager.exception.AuthException;
import com.salon.manager.exception.ErrorResponse;
import com.salon.manager.security.SecurityConfig;
import com.salon.manager.service.AuthService;
import com.salon.manager.service.CustomUserDetailsService;
import com.salon.manager.utils.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            LoginResponse response = authService.authenticateUser(request);
            return ResponseEntity.ok(response);
        } catch (AuthException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorResponse("Invalid credentials"));
        }
    }
}