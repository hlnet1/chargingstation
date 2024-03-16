package com.example.chargingstation.repository;

import com.example.chargingstation.entity.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * Repository interface for Station entity.
 */
@Repository
public interface StationRepository extends JpaRepository<Station, UUID> {
    /**
     * Custom method for finding stations by postal code.
     *
     * @param postCode Postal code
     * @return List of stations.
     */
    public List<Station> findAllByPostCode(String postCode);

    /**
     * Custom query for finding stations within a distance range.
     *
     * @param latitude Latitude
     * @param longitude Longitude
     * @param radius Distance range
     * @return List of stations.
     */
    @Query(value = "SELECT * FROM stations s WHERE (6371 * acos(cos(radians(:latitude)) * cos(radians(s.latitude)) * cos(radians(s.longitude) - radians(:longitude)) + sin(radians(:latitude)) * sin(radians(s.latitude))))  < :radius", nativeQuery = true)
    List<Station> findAllWithingRange(@Param("latitude") Double latitude,@Param("longitude") Double longitude,@Param("radius")  Double radius);
}
