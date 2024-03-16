package com.example.chargingstation.repository;

import com.example.chargingstation.entity.Station;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.UUID;

import static com.example.chargingstation.util.TestConstant.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
@SpringBootTest
class StationRepositoryTest {

    private static final UUID STATION_ID = UUID.randomUUID();

    @Autowired
    public StationRepository repository;

    @Test
    void getByPostCodeCorrectly(){
        assertEquals(21, repository.findAllByPostCode("22017").size());
    }

    @Test
    @DirtiesContext
    void getByIdCorrectly(){
        Station station = repository.save(new Station(STATION_ID,POSTCODE,LATITUDE,LONGITUDE));
        UUID expected = station.getId();
        Station actual = repository.findById(expected).get();
        assertTrue(repository.findById(expected).isPresent());
        assertEquals(expected,actual.getId());
    }


}