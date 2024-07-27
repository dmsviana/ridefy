package br.edu.ifpb.ridefy.repository;

import br.edu.ifpb.ridefy.model.Driver;
import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DriverRepository extends JpaRepository<Driver, Long> {

    @Query(value = "SELECT * FROM tb_drivers d " +
            "WHERE ST_Distance(d.current_location, ST_GeomFromText(ST_AsText(?1), 4326)) < 1000 " +
            "ORDER BY ST_Distance(d.current_location, ST_GeomFromText(ST_AsText(?1), 4326)) " +
            "LIMIT 5",
            nativeQuery = true)
    List<Driver> findFiveNearestDrivers(Point userLocation);

    @Query(value = "SELECT ST_Distance(d.current_location, ST_GeomFromText(ST_AsText(?1), 4326)) " +
            "FROM tb_drivers d " +
            "WHERE d.current_location = ST_GeomFromText(ST_AsText(?2), 4326)",
            nativeQuery = true)
    double calculateDistance(Point userLocation, Point driverLocation);
}
