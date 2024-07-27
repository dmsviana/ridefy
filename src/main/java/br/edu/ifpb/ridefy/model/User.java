package br.edu.ifpb.ridefy.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.locationtech.jts.geom.Point;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "tb_users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String name;

    @Column(columnDefinition = "POINT")
    private Point pickupLocation;

    @Column(columnDefinition = "POINT")
    private Point destinationLocation;
}
