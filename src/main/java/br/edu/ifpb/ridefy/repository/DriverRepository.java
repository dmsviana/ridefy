package br.edu.ifpb.ridefy.repository;

import br.edu.ifpb.ridefy.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DriverRepository extends JpaRepository<Driver, Long> {

    @Query(value = "SELECT * FROM tb_drivers d " +
            "WHERE ST_Distance(ST_GeomFromText(CONCAT('POINT(', ?1, ' ', ?2, ')'), 4326), " +
            "ST_GeomFromText(CONCAT('POINT(', d.latitude, ' ', d.longitude, ')'), 4326)) < 1000 " +
            "ORDER BY ST_Distance(ST_GeomFromText(CONCAT('POINT(', ?1, ' ', ?2, ')'), 4326), " +
            "ST_GeomFromText(CONCAT('POINT(', d.latitude, ' ', d.longitude, ')'), 4326)) " +
            "LIMIT 5",
            nativeQuery = true)
    List<Driver> findFiveNearestDrivers(double latitude, double longitude);



    @Query(value = "SELECT ST_Distance(ST_GeomFromText(CONCAT('POINT(', ?1, ' ', ?2, ')'), 4326), " +
            "ST_GeomFromText(CONCAT('POINT(', ?3, ' ', ?4, ')'), 4326))",
            nativeQuery = true)
    double calculateDistance(double userLatitude, double userLongitude, double driverLatitude, double driverLongitude);
}
