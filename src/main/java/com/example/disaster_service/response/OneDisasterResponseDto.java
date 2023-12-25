package com.example.disaster_service.response;


import com.example.disaster_service.serialization.PolygonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.locationtech.jts.geom.Polygon;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class OneDisasterResponseDto {

    private Long id;
    private String name;
    private String description;
    private LocalDate date;
    private Boolean isActive;

    private List<ZoneResponseDto> zones = new ArrayList<>();

    @JsonSerialize(using = PolygonSerializer.class)
    private Polygon mainZone;



}
