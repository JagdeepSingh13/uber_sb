package com.rideshare.rideservice.dto;

import com.rideshare.rideservice.model.RideStatus;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

public class RideResponse {

    private String id;

    private String riderId;

    private String driverId;

    private double pickUpLatitude;
    private double pickUpLongitude;

    private String pickUpAddress;

    private double dropLatitude;
    private double dropLongitude;

    private String dropAddress;

    private RideStatus status;

    private double actualFare;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private LocalDateTime startedAt;
    private LocalDateTime endedAt;

}
