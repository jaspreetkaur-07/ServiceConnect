package com.sk.servicemarketplace.service;

import com.sk.servicemarketplace.entity.User;
import com.sk.servicemarketplace.entity.WorkerProfile;
import com.sk.servicemarketplace.repository.UserRepository;
import com.sk.servicemarketplace.repository.WorkerProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkerProfileService {

    @Autowired
    private WorkerProfileRepository workerProfileRepository;

    @Autowired
    private UserRepository userRepository;

    public WorkerProfile createOrUpdateProfile(String email, WorkerProfile profileData) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        WorkerProfile profile = new WorkerProfile();
        profile.setUser(user);
        profile.setServiceType(profileData.getServiceType());
        profile.setExperience(profileData.getExperience());
        profile.setHourlyRate(profileData.getHourlyRate());
        profile.setLatitude(profileData.getLatitude());
        profile.setLongitude(profileData.getLongitude());
        profile.setAvailability(true);

        return workerProfileRepository.save(profile);
    }

    public List<WorkerProfile> searchNearbyWorkers(Double lat, Double lng, Double radius, String serviceType) {
        return workerProfileRepository.findNearbyWorkers(lat, lng, radius, serviceType);
    }

    public List<WorkerProfile> getAllWorkers() {
        return workerProfileRepository.findAll();
    }
}
