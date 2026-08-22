package com.sk.servicemarketplace.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "worker_profiles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkerProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String serviceType;
    private Integer experience;
    private Double hourlyRate;
    private Double latitude;
    private Double longitude;
    private Boolean availability = true;
    private Double avgRating = 0.0;
}
