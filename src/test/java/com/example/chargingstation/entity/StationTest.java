package com.example.chargingstation.entity;

import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.UUID;

import static com.example.chargingstation.util.TestConstant.*;
import static org.junit.jupiter.api.Assertions.*;

class StationTest {
;
    private static final UUID STATION_ID = UUID.randomUUID();

    @Test
    public void shouldCreateStationWithPostalCodeAndGeocoordinates() {
        Station station = new Station(POSTCODE,LATITUDE,LONGITUDE);

        assertNull(station.getId());
        assertSame(POSTCODE, station.getPostCode());
        assertSame(LATITUDE, station.getLatitude());
        assertSame(LONGITUDE, station.getLongitude());
    }

    @Test
    public void shouldSetStationId() {
        Station station = new Station();
        ReflectionTestUtils.setField(station, "id", STATION_ID, UUID.class);

        assertEquals(STATION_ID, station.getId());
    }
}