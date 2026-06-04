package com.rideshare.rideservice.dto;

import com.rideshare.rideservice.model.RideStatus;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RideResponse {

    private String id;

    private String riderId;

    private String driverId;

    private double PickupLatitude;
    private double PickupLongitude;

    private String PickupAddress;

    private double dropLatitude;
    private double dropLongitude;

    private String dropAddress;

    private RideStatus status;

    private double actualFare;
    private double estimatedFare;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private LocalDateTime startedAt;
    private LocalDateTime completedAt;

}
