package com.rideshare.rideservice.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RideRequest {

    private String riderId;

    private double PickupLatitude;
    private double PickupLongitude;

    private String PickupAddress;

    private double dropLatitude;
    private double dropLongitude;

    private String dropAddress;

}
