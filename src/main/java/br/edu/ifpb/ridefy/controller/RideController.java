package br.edu.ifpb.ridefy.controller;

import br.edu.ifpb.ridefy.model.Driver;
import br.edu.ifpb.ridefy.model.Ride;
import br.edu.ifpb.ridefy.model.User;
import br.edu.ifpb.ridefy.model.enums.RideStatus;
import br.edu.ifpb.ridefy.repository.DriverRepository;
import br.edu.ifpb.ridefy.repository.RideRepository;
import br.edu.ifpb.ridefy.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Point;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.util.List;

@RestController
@RequestMapping("/rides")
@RequiredArgsConstructor
public class RideController {

    private final RideRepository rideRepository;
    private final DriverRepository driverRepository;
    private final UserRepository userRepository;

    @GetMapping("/request-ride/{userId}")
    public List<Driver> requestRide(@PathVariable Long userId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Usuário não encontrado."));

        return driverRepository.findFiveNearestDrivers(user.getPickupLocation());
    }


    @PostMapping("/accept/{driverId}/{userId}")
    public Ride acceptRide(@PathVariable Long driverId, @PathVariable Long userId) {

        Driver driver = driverRepository.findById(driverId).orElseThrow(() -> new RuntimeException("Motorista não encontrado."));
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Usuário não encontrado."));

        double distance = driverRepository.calculateDistance(user.getPickupLocation(), driver.getCurrentLocation());

        Ride ride = new Ride();
        ride.setUser(user);
        ride.setDriver(driver);

        //formatar distancia
        DecimalFormat df = new DecimalFormat("#.##");

        distance = Double.parseDouble(df.format(distance).replace(",", "."));
        ride.setDistance(distance);
        ride.setRideStatus(RideStatus.ACCEPTED);
        return rideRepository.save(ride);
    }
}
