package com.example.chargingstation.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "stations")
public class Station {

    /**
     * Primary key for table stations UUID type auto generated
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @ColumnDefault("random_uuid()")
    private UUID id;

    /**
     * Text field Postal code
     */
    @Column(length = 5, name ="post_code",nullable = false)
    private String postCode;

    /**
     * Decimal geocoordinates latitude
     */
    @Column(nullable = false)
    private Double latitude;

    /**
     * Decimal geocoordinates longitude
     */
    @Column(nullable = false)
    private Double longitude;

    /**
     * Station Constructor from postal code and geocoordinates.
     *
     * @param postCode
     * @param latitude
     * @param longitude
     * @return an object
     */
    public Station(String postCode, Double latitude, Double longitude) {
        this.postCode = postCode;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
