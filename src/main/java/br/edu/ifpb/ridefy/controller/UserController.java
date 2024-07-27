package br.edu.ifpb.ridefy.controller;

import br.edu.ifpb.ridefy.model.Ride;
import br.edu.ifpb.ridefy.model.User;
import br.edu.ifpb.ridefy.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final GeometryFactory geometryFactory;

    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        Point pickupLocation = geometryFactory.createPoint(user.getPickupLocation().getCoordinate());
        Point destinationLocation = geometryFactory.createPoint(user.getDestinationLocation().getCoordinate());

        user.setPickupLocation(pickupLocation);
        user.setDestinationLocation(destinationLocation);
        return userRepository.save(user);
    }

}
