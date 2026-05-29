package com.rideshare.locationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// send data to matching service
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NearbyDriverResponse {

    private String driverId;
    private double latitude;
    private double longitude;
    private double distanceInKm;

}
