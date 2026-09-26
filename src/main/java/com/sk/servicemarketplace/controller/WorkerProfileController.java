package com.sk.servicemarketplace.controller;

import com.sk.servicemarketplace.entity.WorkerProfile;
import com.sk.servicemarketplace.service.WorkerProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/workers")
public class WorkerProfileController {

    @Autowired
    private WorkerProfileService workerProfileService;

    @PostMapping("/profile")
    public ResponseEntity<?> createProfile(@RequestBody WorkerProfile profileData, Authentication authentication) {
        try {
            String email = authentication.getName();
            WorkerProfile profile = workerProfileService.createOrUpdateProfile(email, profileData);
            return ResponseEntity.ok(profile);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchWorkers(
            @RequestParam Double lat,
            @RequestParam Double lng,
            @RequestParam(defaultValue = "10") Double radius,
            @RequestParam String serviceType) {

        List<WorkerProfile> workers = workerProfileService.searchNearbyWorkers(lat, lng, radius, serviceType);
        return ResponseEntity.ok(workers);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllWorkers() {
        return ResponseEntity.ok(workerProfileService.getAllWorkers());
    }
}