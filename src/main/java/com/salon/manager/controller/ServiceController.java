package com.salon.manager.controller;

import com.salon.manager.dto.requests.ServiceRequest;
import com.salon.manager.dto.responses.ServiceResponse;
import com.salon.manager.model.Service;
import com.salon.manager.model.ServiceCategory;
import com.salon.manager.repository.ServiceRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/services")
public class ServiceController {

    private final ServiceRepository serviceRepository;

    public ServiceController(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @GetMapping
    public List<ServiceResponse> getAllServices() {
        return serviceRepository.findAll().stream()
                .map(ServiceResponse::new)
                .toList();
    }

    @GetMapping("/active")
    public List<ServiceResponse> getActiveServices() {
        return serviceRepository.findByActive(true).stream()
                .map(ServiceResponse::new)
                .toList();
    }

    @PostMapping
    public ResponseEntity<ServiceResponse> createService(
            @Validated @RequestBody ServiceRequest request) {

        Service service = new Service();
        service.setName(request.name());
        service.setDescription(request.description());
        service.setDurationMinutes(request.durationMinutes());
        service.setPrice(request.price());
        service.setCategory(request.category());
        service.setActive(request.active());

        Service savedService = serviceRepository.save(service);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ServiceResponse(savedService));
    }

    @GetMapping("/category/{category}")
    public List<Service> getServicesByCategory(
            @PathVariable ServiceCategory category,
            @RequestParam(defaultValue = "true") boolean activeOnly) {

        return activeOnly
                ? serviceRepository.findByActiveAndCategory(true, category)
                : serviceRepository.findByCategory(category);
    }

    @PatchMapping("/{id}/status")
    public Service updateServiceStatus(
            @PathVariable Long id,
            @RequestParam boolean active) {

        Service service = serviceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Service not found"));

        service.setActive(active);
        return serviceRepository.save(service);
    }
}