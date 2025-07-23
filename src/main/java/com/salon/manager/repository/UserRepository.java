package com.salon.manager.repository;

import com.salon.manager.model.User;
import com.salon.manager.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email); // Now works because User is an entity

    // Optional: Find by role
    List<User> findByRole(UserRole role);
}