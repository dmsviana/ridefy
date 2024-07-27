package br.edu.ifpb.ridefy.model;


import br.edu.ifpb.ridefy.model.enums.RideStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "tb_rides")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Ride {


    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;

    @Column(name = "distance")
    private double distance;

    @Enumerated(STRING)
    private RideStatus rideStatus;
}