package com.example.chargingstation.controller;


import com.example.chargingstation.exception.ObjectNotFoundException;
import com.example.chargingstation.service.CarChargingService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static com.example.chargingstation.util.TestConstant.*;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class CarChargingControllerTest {

    private static final String BASE_URL = "http://localhost:8080/stations/";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarChargingService service;

    @Test
    @DirtiesContext
    void testGetStationByIdThrowException() throws Exception {
        Mockito.when(service.getStationById(any(UUID.class))).thenThrow(ObjectNotFoundException.class);

        mockMvc.perform(get(BASE_URL + STATION_1.getId()))
                .andDo(print())
                .andExpect(status().isNotFound());

        Mockito.verify(service).getStationById(any(UUID.class));
    }

    @Test
    @DirtiesContext
    void testGetStationByIdCorrectly() throws Exception {
        Mockito.when(service.getStationById(any())).thenReturn(STATION_2);

        mockMvc.perform(get(BASE_URL + STATION_2.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("postCode").value("22017"))
                .andExpect(jsonPath("latitude").value(STATION_2.getLatitude()))
                .andExpect(jsonPath("longitude").value(STATION_2.getLongitude())
                );
        Mockito.verify(service).getStationById(any(UUID.class));
    }

    @Test
    void testGetStationByPostCodeCorrectly() throws Exception {
    Mockito.when(service.getStationsByPostCode(any(String.class))).thenReturn(List.of(STATION_2,STATION_3));

        mockMvc.perform(get(BASE_URL + "postcode?postCode=" + "22017")).
                andExpect(status().isOk()).
                andExpect(jsonPath("$", hasSize(2))).
                andExpect(jsonPath("$.[0].postCode", is(STATION_2.getPostCode()))).
                andExpect(jsonPath("$.[0].latitude", is(STATION_2.getLatitude()))).
                andExpect(jsonPath("$.[0].longitude", is(STATION_2.getLongitude()))).
                andExpect(jsonPath("$.[1].postCode", is(STATION_3.getPostCode()))).
                andExpect(jsonPath("$.[1].latitude", is(STATION_3.getLatitude()))).
                andExpect(jsonPath("$.[1].longitude", is(STATION_3.getLongitude())));

        Mockito.verify(service).getStationsByPostCode(any(String.class));

    }

    @Test
    void testGetStationByDistanceRangeReturnsEmptyList() throws Exception {
        Mockito.when(service.getStationsWithinDistance(any(Double.class),any(Double.class),any(Double.class)))
                .thenReturn(List.of());

        mockMvc.perform(get(BASE_URL + "distance?latitude=" + 49.27333333 +"&longitude=" + 2.265277778 + "&radius=" + 105.0)).
                andDo(print()).
                andExpect(status().isOk()).
                andExpect(jsonPath("$", hasSize(0)));

        Mockito.verify(service).getStationsWithinDistance(any(Double.class),any(Double.class),any(Double.class));

    }

    @Test
    void testGetStationByDistanceRangeCorrectly() throws Exception {
        Mockito.when(service.getStationsWithinDistance(any(Double.class),any(Double.class),any(Double.class)))
                .thenReturn(List.of(STATION_1,STATION_2,STATION_3,STATION_4));


        mockMvc.perform(get(BASE_URL + "distance?latitude=" + 49.27333333 +"&longitude=" + 2.265277778 + "&radius=" + 0.0)).
                andDo(print()).
                andExpect(status().isOk()).
                andExpect(jsonPath("$", hasSize(4))).
                andExpect(jsonPath("$.[1].postCode", is(STATION_2.getPostCode()))).
                andExpect(jsonPath("$.[1].latitude", is(STATION_2.getLatitude()))).
                andExpect(jsonPath("$.[1].longitude", is(STATION_2.getLongitude()))).
                andExpect(jsonPath("$.[2].postCode", is(STATION_3.getPostCode()))).
                andExpect(jsonPath("$.[2].latitude", is(STATION_3.getLatitude()))).
                andExpect(jsonPath("$.[2].longitude", is(STATION_3.getLongitude()))).
                andExpect(jsonPath("$.[0].postCode", is(STATION_1.getPostCode()))).
                andExpect(jsonPath("$.[0].latitude", is(STATION_1.getLatitude()))).
                andExpect(jsonPath("$.[0].longitude", is(STATION_1.getLongitude()))).
                andExpect(jsonPath("$.[3].postCode", is(STATION_4.getPostCode()))).
                andExpect(jsonPath("$.[3].latitude", is(STATION_4.getLatitude()))).
                andExpect(jsonPath("$.[3].longitude", is(STATION_4.getLongitude())));

        Mockito.verify(service).getStationsWithinDistance(any(Double.class),any(Double.class),any(Double.class));

    }




}