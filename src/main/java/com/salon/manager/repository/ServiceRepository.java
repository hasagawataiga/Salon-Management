package com.salon.manager.repository;

import com.salon.manager.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceRepository extends JpaRepository<Service, Long> {
    List<Service> findByActiveTrue();
}
