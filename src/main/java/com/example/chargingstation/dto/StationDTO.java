package com.example.chargingstation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
/**
 * Station DTO
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StationDTO {
    private  UUID id;
    private String postCode;
    private Double latitude;
    private Double longitude;
}
