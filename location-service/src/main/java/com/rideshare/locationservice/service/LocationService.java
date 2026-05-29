package com.rideshare.locationservice.service;

import com.rideshare.locationservice.dto.DriverLocationRequest;
import com.rideshare.locationservice.dto.NearbyDriverResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.geo.*;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class LocationService {

    private final RedisTemplate<String, String> redisTemplate;

//    redis key for all driver locations
    private static final String DRIVERS_GEO_KEY = "drivers:locations";

//    maps to redis GEOADD command
    public void updateDriverLocation(DriverLocationRequest request) {
        log.info("updating location for driver: {}", request.getDriverId());

        Point driverPoint = new Point(
                request.getLongitude(),
                request.getLatitude()
        );

        redisTemplate.opsForGeo().add(
                DRIVERS_GEO_KEY,
                driverPoint,
                request.getDriverId()
        );

        log.info("location updated for driver: {}", request.getDriverId());
    }

    public List<NearbyDriverResponse> findNearbyDrivers(double latitude, double longitude, double radius) {
        log.info("finding drivers near: {}-{}", latitude, longitude);

        Circle searchArea = new Circle(
                new Point(longitude, latitude),
                new Distance(radius, Metrics.KILOMETERS)
        );

        GeoResults<RedisGeoCommands.GeoLocation<String>> results =
                redisTemplate.opsForGeo().radius(
                        DRIVERS_GEO_KEY,
                        searchArea,
                        RedisGeoCommands.GeoRadiusCommandArgs.newGeoRadiusArgs()
                                .includeCoordinates()
                                .includeDistance()
                                .sortAscending()
                                .limit(10)
                );

        List<NearbyDriverResponse> nearbyDrivers = new ArrayList<>();
        if (results != null) {
            results.getContent().forEach(
                    result -> {
                        RedisGeoCommands.GeoLocation<String> loc = result.getContent();
                        nearbyDrivers.add(new NearbyDriverResponse(
                                loc.getName(),
                                loc.getPoint().getY(),
                                loc.getPoint().getX(),
                                result.getDistance().getValue()
                        ));
                    });
        }

        log.info("found {} drivers nearby", nearbyDrivers.size());

        return nearbyDrivers;
    }

    public void removeDriver(String driverId) {
        log.info("removing driver: {}", driverId);

        redisTemplate.opsForGeo().remove(
                DRIVERS_GEO_KEY,
                driverId
        );
    }

}
