package com.sk.servicemarketplace.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User customer;

    @ManyToOne
    @JoinColumn(name = "worker_id")
    private User worker;

    private String serviceType;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    private LocalDateTime scheduledTime;
    private String address;
    private LocalDateTime createdAt = LocalDateTime.now();
}
