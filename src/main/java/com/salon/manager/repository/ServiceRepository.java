package com.salon.manager.repository;

import com.salon.manager.model.Service;
import com.salon.manager.model.ServiceCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {
    List<Service> findByActive(boolean active);

    List<Service> findByActiveAndCategory(boolean b, ServiceCategory category);

    List<Service> findByCategory(ServiceCategory category);
}
