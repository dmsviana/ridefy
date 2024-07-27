package br.edu.ifpb.ridefy.repository;

import br.edu.ifpb.ridefy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
