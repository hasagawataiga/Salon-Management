package com.salon.manager.service;

import com.salon.manager.model.User;
import com.salon.manager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private static final Logger log = LoggerFactory.getLogger(CustomUserDetailsService.class);

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> userOpt = userRepository.findByEmail(email);

        if (userOpt.isEmpty()) {
            log.error("User not found with email: {}", email);
            throw new UsernameNotFoundException("User not found");
        }

        User user = userOpt.get();
        log.debug("Loaded user: {}", user.getEmail());
        log.debug("Active status: {}", user.isActive());
        log.debug("Role: {}", user.getRole());

        return new CustomUserDetails(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                user.getRole(),
                user.isActive()
        );
    }
//
//    private final UserRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String email)
//            throws UsernameNotFoundException {
//        System.out.println("CustomUserDetailsService - Searching for email: " + email);
//        User user = userRepository.findByEmail(email)
//                .orElseThrow(() -> {
//                    System.err.println("USER NOT FOUND: " + email);
//                    return new UsernameNotFoundException("User not found with email: " + email);
//                });
//
//        // DEBUG: Print loaded user details
//        System.out.println("Loaded user: " + user.getEmail());
//        System.out.println("Password: " + user.getPassword());
//        System.out.println("Role: " + user.getRole().name());
//
//        return new org.springframework.security.core.userdetails.User(
//                user.getEmail(),
//                user.getPassword(),
//                Collections.singletonList(new SimpleGrantedAuthority(user.getRole().name()))
//        );
//    }
}
