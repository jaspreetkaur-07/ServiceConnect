package com.sk.servicemarketplace.repository;

import com.sk.servicemarketplace.entity.WorkerProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface WorkerProfileRepository extends JpaRepository<WorkerProfile, Long> {

    @Query(value = "SELECT * FROM worker_profiles w WHERE " +
            "(6371 * acos(cos(radians(:lat)) * cos(radians(w.latitude)) * " +
            "cos(radians(w.longitude) - radians(:lng)) + sin(radians(:lat)) * " +
            "sin(radians(w.latitude)))) < :radius AND w.service_type = :serviceType",
            nativeQuery = true)
    List<WorkerProfile> findNearbyWorkers(@Param("lat") Double lat,
                                          @Param("lng") Double lng,
                                          @Param("radius") Double radius,
                                          @Param("serviceType") String serviceType);
}
