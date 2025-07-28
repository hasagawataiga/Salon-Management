package com.salon.manager.tests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

// Add this temporary test to verify password matching
@SpringBootTest
class PasswordVerificationTest {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    void testPasswordMatch() {
        String rawPassword = "password123";
//        String storedHash = "$2a$10$XptfskLsT1l/bRTLRiiCgejHqOpgXFreUnNUa35gJdCr2v2QbVFzu";
        String storedHash = "$2a$10$ofCT1fmJlMVs8A0DyhuUIeBPfCNeYSBPpbg7qmlGu0OfbJ5iMKUWW";
        boolean matches = passwordEncoder.matches(rawPassword, storedHash);
        System.out.println("Password matches: " + matches);

        assertThat(matches).isTrue();
    }
}
