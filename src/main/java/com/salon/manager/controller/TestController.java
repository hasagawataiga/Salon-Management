package com.salon.manager.controller;

import com.salon.manager.dto.ServiceDTO;
import com.salon.manager.model.Service;
import com.salon.manager.repository.ServiceRepository;
import com.salon.manager.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/test")
@RequiredArgsConstructor
public class TestController {

    private final CustomUserDetailsService userDetailsService;
    private final ServiceRepository serviceRepository;

    @GetMapping
    public String hello() {
        return "Hello from Spring Boot!";
    }


    @GetMapping("/load-user")
    public ResponseEntity<?> testLoadUser(@RequestParam String email) {
        System.out.println("Testing with email: " + email);
        try {
            UserDetails user = userDetailsService.loadUserByUsername(email);
            System.out.println("Loaded user: " + user.getUsername());
            return ResponseEntity.ok("User loaded: " + user.getUsername());
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @GetMapping("/active-services")
    public List<ServiceDTO> getActiveServices() {
        return serviceRepository.findByActive(true).stream()
                .map(ServiceDTO::new)
                .toList();
    }

    @GetMapping("/inactive-services")
    public List<ServiceDTO> getInactiveServices() {
        return serviceRepository.findByActive(false).stream()
                .map(ServiceDTO::new)
                .toList();
    }
}