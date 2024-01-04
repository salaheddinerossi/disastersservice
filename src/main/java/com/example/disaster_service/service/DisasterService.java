package com.example.disaster_service.service;


import com.example.disaster_service.dto.DisasterDto;
import com.example.disaster_service.model.Disaster;
import com.example.disaster_service.model.Zone;
import com.example.disaster_service.response.DisasterResponseDto;
import com.example.disaster_service.response.OneDisasterResponseDto;
import com.example.disaster_service.response.ZoneResponseDto;
import org.locationtech.jts.io.ParseException;

import java.io.IOException;
import java.util.List;

public interface DisasterService {

    public Disaster createDisaster(DisasterDto disasterDto) throws IOException, ParseException;

    public List<DisasterResponseDto> getAllDisasters();

    public OneDisasterResponseDto getDisaster(Long id);

    public List<ZoneResponseDto> getDisastersWithZones();


}
