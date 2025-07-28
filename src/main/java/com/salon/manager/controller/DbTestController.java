package com.salon.manager.controller;

import com.salon.manager.repository.UserRepository;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/db-test")
@RequiredArgsConstructor
public class DbTestController {

    private final UserRepository userRepository;

    @GetMapping("/user-exists")
    public ResponseEntity<?> userExists(@RequestParam String email) {
        System.out.println("Checking user .....");
        boolean exists = userRepository.findByEmail(email).isPresent();
        return ResponseEntity.ok(Map.of("email", email, "exists", exists));
    }
}
