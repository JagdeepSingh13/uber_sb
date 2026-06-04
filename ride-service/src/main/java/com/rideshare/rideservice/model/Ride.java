package com.rideshare.rideservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "rides")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ride {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String riderId;

    @Column(nullable = false)
    private String driverId;

    @Column(nullable = false)
    private double PickupLatitude;
    @Column(nullable = false)
    private double PickupLongitude;

    private String PickupAddress;

    private double dropLatitude;
    private double dropLongitude;

    private String dropAddress;

    @Enumerated(EnumType.STRING)
    private RideStatus status;

    private double estimatedFare;
    private double actualFare;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    private LocalDateTime startedAt;
    private LocalDateTime completedAt;

}
