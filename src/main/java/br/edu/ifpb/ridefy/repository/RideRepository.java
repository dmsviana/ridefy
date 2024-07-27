package br.edu.ifpb.ridefy.repository;

import br.edu.ifpb.ridefy.model.Ride;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RideRepository extends JpaRepository<Ride, Long> {
}
