package com.example.disaster_service.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import jakarta.persistence.*;
import lombok.Data;

import org.locationtech.jts.geom.Polygon;
@Data
@Entity(name = "zone")
public class Zone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(columnDefinition = "geometry(Polygon,4326)")
    private Polygon geometry;

    @ManyToOne
    @JoinColumn(name = "disaster_id")
    private Disaster disaster;

}
