package com.salon.manager.repository;

import com.salon.manager.model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ManagerRepository extends JpaRepository<Manager, Long> {
    List<Manager> findByCanAuditTrue();
}