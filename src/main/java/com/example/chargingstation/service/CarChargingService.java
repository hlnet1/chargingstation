package com.example.chargingstation.service;

import com.example.chargingstation.dto.StationDTO;

import java.util.List;
import java.util.UUID;

/**
 * CarService interface for business logic.
 */
public interface CarChargingService {
    /**
     * Search for station by id
     *
     * @param id Unique user id
     * @return station.
     */
    StationDTO getStationById (UUID id);

    /**
     * Search for stations by postal code
     *
     * @param postCode Postal code
     * @return list of stations.
     */
    List<StationDTO> getStationsByPostCode (String postCode);

    /**
     * Search for stations within distance
     *
     * @param latitude Latitude
     * @param longitude Longitude
     * @param radius Distance range
     * @return List of stations.
     */
    List<StationDTO> getStationsWithinDistance (Double latitude, Double longitude, Double radius);
}
