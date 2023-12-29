package com.example.disaster_service.response;

import com.example.disaster_service.serialization.PolygonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.locationtech.jts.geom.Polygon;

@Data
public class ZoneResponseDto {

    private Long id;

    private String name;

    @JsonSerialize(using = PolygonSerializer.class)
    private Polygon geometry;


    //private OneDisasterResponseDto disaster;

}
