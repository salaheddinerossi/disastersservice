package com.example.disaster_service.response;

import com.example.disaster_service.serialization.PointSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.locationtech.jts.geom.Point;

import java.time.LocalDate;

@Data
public class DisasterResponseDto {
    private Long id;
    private String name;
    private String description;
    private LocalDate date;
    private Boolean isActive;

    @JsonSerialize(using = PointSerializer.class)
    private Point center;

}
