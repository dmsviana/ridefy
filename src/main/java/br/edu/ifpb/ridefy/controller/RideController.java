package br.edu.ifpb.ridefy.controller;



import br.edu.ifpb.ridefy.model.Driver;
import br.edu.ifpb.ridefy.model.Ride;
import br.edu.ifpb.ridefy.model.User;
import br.edu.ifpb.ridefy.repository.DriverRepository;
import br.edu.ifpb.ridefy.repository.RideRepository;
import br.edu.ifpb.ridefy.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rides")
@RequiredArgsConstructor
public class RideController {


    private final RideRepository rideRepository;
    private final DriverRepository driverRepository;
    private final UserRepository userRepository;

    @PostMapping("/request-ride/{userId}")
    public List<Driver> requestRide(@PathVariable Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.save(user);
        return driverRepository.findFiveNearestDrivers(user.getLatitude(), user.getLongitude());
    }

    @PostMapping("/accept/{driverId}/{userId}")
    public Ride acceptRide(@PathVariable Long driverId, @PathVariable Long userId) {
        Driver driver = driverRepository.findById(driverId).orElseThrow(() -> new RuntimeException("Driver not found"));
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        double distance = driverRepository.calculateDistance(user.getLatitude(), user.getLongitude(), driver.getLatitude(), driver.getLongitude());

        Ride ride = new Ride();
        ride.setDriver(driver);
        ride.setUser(user);
        ride.setDistance(distance);

        return rideRepository.save(ride);
    }

    @GetMapping("/requested")
    public List<Ride> getRequestedRides() {
        return rideRepository.findAll();
    }

    @GetMapping
    public List<Ride> getAllRides() {
        return rideRepository.findAll();
    }

    @PostMapping
    public Ride createRide(@RequestBody Ride ride) {
        return rideRepository.save(ride);
    }
}

