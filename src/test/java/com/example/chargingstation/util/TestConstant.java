package com.example.chargingstation.util;

import com.example.chargingstation.dto.StationDTO;

import java.util.UUID;

public class TestConstant {
    public static final StationDTO STATION_1 = new StationDTO(UUID.randomUUID(),"22017", 49.27333333, 2.265277778);
    public static final StationDTO STATION_2 = new StationDTO(UUID.randomUUID(),"22017", 49.31805556, 2.307777778);
    public static final StationDTO STATION_3 = new StationDTO(UUID.randomUUID(),"22017", 49.32194444, 2.094722222);
    public static final StationDTO STATION_4 = new StationDTO(UUID.randomUUID(),"22017", 49.29, 2.205833333);

    public static final String POSTCODE = "22077";
    public static final Double LONGITUDE = 2.068888889;
    public static final Double LATITUDE = 49.24333333;
    public static Double LAT = 49.278011;
    public static Double LONG = 2.092911;
    public static Double RADIUS = 5.5;

}
