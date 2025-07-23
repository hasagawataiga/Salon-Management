package com.salon.manager.repository;

import com.salon.manager.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByActiveTrue();
    List<Employee> findByServicesId(Long serviceId);
}
