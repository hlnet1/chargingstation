package com.example.chargingstation.controller;

import com.example.chargingstation.dto.StationDTO;
import com.example.chargingstation.exception.ObjectNotFoundException;
import com.example.chargingstation.service.CarChargingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/stations")
@Slf4j
public class CarChargingController {

    private final CarChargingService service;


    public CarChargingController(CarChargingService service) {
        this.service = service;
    }

    /**
     * Get station by Postal code.
     *
     * @param postCode
     * @return ResponseEntity with status 200 (OK) and list the stations
     */
    @GetMapping(value = "/postcode")
    public ResponseEntity<List<StationDTO>> getAllByPostCode(@RequestParam("postCode") String postCode) {
        return ResponseEntity.ok(service.getStationsByPostCode(postCode));
    }

    /**
     * Get station by ID.
     *
     * @param id the ID of the required station
     * @return ResponseEntity with status 200 (OK) and with body of the station, or with status 404 (Not Found) if the station does not exist
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<StationDTO> getStationById (@PathVariable UUID id) {
        return ResponseEntity.ok(service.getStationById(id));
    }

    /**
     * Get station by Postal code.
     *
     * @param longitude
     * @param latitude
     * @param radius
     * @return ResponseEntity with status 200 (OK) and list the stations
     */
    @GetMapping(value = "/distance")
    public ResponseEntity<List<StationDTO>> getAllWithinRange (@RequestParam("latitude") Double latitude, @RequestParam("longitude") Double longitude, @RequestParam("radius") Double radius) {
        return ResponseEntity.ok(service.getStationsWithinDistance(latitude,longitude,radius));
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({ ObjectNotFoundException.class })
    public void handleNotFound(Exception ex) {
        log.error("Exception is: ", ex);
    }
}
