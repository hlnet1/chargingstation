package com.example.chargingstation.service.impl;

import com.example.chargingstation.dto.StationDTO;
import com.example.chargingstation.entity.Station;
import com.example.chargingstation.exception.ObjectNotFoundException;
import com.example.chargingstation.repository.StationRepository;
import com.example.chargingstation.service.CarChargingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
/**
 * CarChargingService interface implementation
 */
@Service
public class CarChargingServiceImpl implements CarChargingService {

    private final StationRepository stationRepository;

    public CarChargingServiceImpl(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

    /**
     * Search for stations by id and throw an exception if station with particular id is not found
     */
    public StationDTO getStationById (UUID id){
        Optional<Station> stationOpt = stationRepository.findById(id);

        if (stationOpt == null) {
            throw new ObjectNotFoundException("Station with id " + id + " was not found!");
        }

        return this.mapToDto(stationOpt.get());
    }

    /**
     * Search for stations by postal code and return null with message not found
     */
    public List<StationDTO> getStationsByPostCode (String postCode){
        var stationList = stationRepository.
                findAllByPostCode(postCode);

        if (stationList.isEmpty()) {
            throw new ObjectNotFoundException("No stations for " + postCode + " were not found!");
        }

        return stationList.
                stream().
                map(this::mapToDto).
                collect(Collectors.toList());
    }
    /**
     * Search for stations within distance range and return null with message not found
     */
    @Override
    public List<StationDTO> getStationsWithinDistance(Double latitude, Double longitude, Double radius) {
        var stationsWithinDistance = stationRepository.
                findAllWithingRange(latitude,longitude,radius);

        if (stationsWithinDistance.isEmpty()) {
            log.info("No stations within " + radius + "km were not found!");
        }

        return stationsWithinDistance.
                stream().
                map(this::mapToDto).
                collect(Collectors.toList());

    }

    /**
     * Method mapping an entity to dto
     */

    private StationDTO mapToDto(Station station) {

            StationDTO stationDTO = new StationDTO();

            stationDTO.setId(station.getId());
            stationDTO.setPostCode(station.getPostCode());
            stationDTO.setLatitude(station.getLatitude());
            stationDTO.setLongitude(station.getLongitude());

            return stationDTO;
        }

}
