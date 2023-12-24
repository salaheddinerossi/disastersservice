package com.example.disaster_service.service;


import com.example.disaster_service.dto.DisasterDto;
import com.example.disaster_service.model.Disaster;
import com.example.disaster_service.model.Zone;
import org.locationtech.jts.io.ParseException;

import java.io.IOException;

public interface DisasterService {

    public Disaster createDisaster(DisasterDto disasterDto) throws IOException, ParseException;

    public Zone createZone(Zone zone);


}
