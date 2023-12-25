package com.example.disaster_service.model;


import com.example.disaster_service.serialization.PointSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.Data;
import org.locationtech.jts.geom.Point;


@Data
@Entity(name = "declaration")
public class Declaration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "phone_number")
    private Integer phoneNumber;

    private String address;

    private String description;

    @JsonSerialize(using = PointSerializer.class)
    private Point localisation;

    private Boolean isArchived;

}
