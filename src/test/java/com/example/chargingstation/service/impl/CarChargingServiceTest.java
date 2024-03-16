package com.example.chargingstation.service.impl;

import com.example.chargingstation.dto.StationDTO;
import com.example.chargingstation.entity.Station;
import com.example.chargingstation.exception.ObjectNotFoundException;
import com.example.chargingstation.repository.StationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import static com.example.chargingstation.util.TestConstant.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
class CarChargingServiceTest {

    @Mock
    private StationRepository repositoryMock;

    private CarChargingServiceImpl service;

    @BeforeEach
    void setUp() {
        service =
                new CarChargingServiceImpl(repositoryMock);
    }


    @Test
    void getStationByIdCorrectly(){
        when(repositoryMock.findById(STATION_1.getId())).thenReturn(Optional.of(new Station(STATION_1.getId(),STATION_1.getPostCode(),STATION_1.getLatitude(),STATION_1.getLongitude())));
        StationDTO expected = STATION_1;
        assertEquals(expected, service.getStationById(STATION_1.getId()));
   }
    @Test
    void getStationByIdThrowException(){
        when(repositoryMock.findById(STATION_1.getId())).thenReturn(null);
        assertThrows(
                ObjectNotFoundException.class,
                () -> service.getStationById(STATION_1.getId())
        );
    }
    @Test
    void getStationListByPostCodeCorrectly(){
        when(repositoryMock.findAllByPostCode(STATION_1.getPostCode())).thenReturn(List.of(
                new Station(STATION_1.getId(),STATION_1.getPostCode(),STATION_1.getLatitude(),STATION_1.getLongitude()),
                new Station(STATION_2.getId(),STATION_2.getPostCode(),STATION_2.getLatitude(),STATION_2.getLongitude()),
                new Station(STATION_3.getId(),STATION_3.getPostCode(),STATION_3.getLatitude(),STATION_3.getLongitude())
        ));
        List<StationDTO> expected = List.of(STATION_1,STATION_2,STATION_3);
        assertEquals(expected, service.getStationsByPostCode(STATION_1.getPostCode()));
    }

    @Test
    void getStationListByPostCodeThrowException(){
        when(repositoryMock.findAllByPostCode(STATION_1.getPostCode())).thenReturn(List.of());
        assertThrows(
                ObjectNotFoundException.class,
                () -> service.getStationsByPostCode(STATION_1.getPostCode())
        );
    }

    @Test
    void getStationListByDistanceCorrectly(){
        when(repositoryMock.findAllWithingRange(LAT,LONG,RADIUS)).thenReturn(List.of(
                new Station(STATION_1.getId(),STATION_1.getPostCode(),STATION_1.getLatitude(),STATION_1.getLongitude()),
                new Station(STATION_2.getId(),STATION_2.getPostCode(),STATION_2.getLatitude(),STATION_2.getLongitude()),
                new Station(STATION_3.getId(),STATION_3.getPostCode(),STATION_3.getLatitude(),STATION_3.getLongitude()),
                new Station(STATION_4.getId(),STATION_4.getPostCode(),STATION_4.getLatitude(),STATION_4.getLongitude())
        ));
        List<StationDTO> expected = List.of(STATION_1,STATION_2,STATION_3,STATION_4);
        assertEquals(expected, service.getStationsWithinDistance(LAT,LONG,RADIUS));
    }

    @Test
    void getStationListByDistanceReturnNull(){
        when(repositoryMock.findAllWithingRange(LAT,LONG,RADIUS)).thenReturn(List.of());
        assertEquals(List.of(), service.getStationsWithinDistance(LAT,LONG,RADIUS));
    }
}