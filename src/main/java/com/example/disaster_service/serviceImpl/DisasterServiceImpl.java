package com.example.disaster_service.serviceImpl;

import com.example.disaster_service.dto.DisasterDto;
import com.example.disaster_service.exeception.DisasterNotFoundException;
import com.example.disaster_service.model.Disaster;
import com.example.disaster_service.model.Zone;
import com.example.disaster_service.repository.DisasterRepository;
import com.example.disaster_service.repository.ZoneRepository;
import com.example.disaster_service.response.DisasterResponseDto;
import com.example.disaster_service.response.OneDisasterResponseDto;
import com.example.disaster_service.response.ZoneResponseDto;
import com.example.disaster_service.service.DisasterService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.Polygon;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.geojson.GeoJsonReader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Service
public class DisasterServiceImpl implements DisasterService {

    final
    DisasterRepository disasterRepository;

    final
    ZoneRepository zoneRepository;

    public DisasterServiceImpl(DisasterRepository disasterRepository, ZoneRepository zoneRepository) {
        this.disasterRepository = disasterRepository;
        this.zoneRepository = zoneRepository;
    }





    @Transactional
    @Override
    public Disaster createDisaster(DisasterDto disasterDto) throws IOException, ParseException {

        MultipartFile file = disasterDto.getFile();
        InputStream inputStream = file.getInputStream();
        ObjectMapper jsonMapper = new ObjectMapper();
        GeoJsonReader geoJsonReader = new GeoJsonReader();

        JsonNode geoJson = jsonMapper.readTree(inputStream);
        Disaster disaster = new Disaster();
        disaster.setName(disasterDto.getName());
        disaster.setDescription(disasterDto.getDescription());
        disaster.setDate(LocalDate.now());
        disaster.setIsActive(true);



        for (JsonNode feature : geoJson.path("features")) {
            String zoneName = feature.path("properties").path("name").asText();
            Geometry geometry = geoJsonReader.read(feature.path("geometry").toString());
            geometry.setSRID(4326);

            if ("main".equals(zoneName)) {
                disaster.setMainZone((Polygon) geometry); // Set the main zone geometry
            } else {
                Zone zone = new Zone();
                zone.setName(zoneName);
                zone.setGeometry((Polygon) geometry);
                zone.setDisaster(disaster);
                disaster.getZones().add(zone);
            }
        }

        disasterRepository.save(disaster);

        return disaster;
    }

    @Override
    public List<DisasterResponseDto> getAllDisasters() {
        List<Disaster> disasters = disasterRepository.findAll();
        List<DisasterResponseDto> disasterDtos = new ArrayList<>();

        for (Disaster disaster : disasters) {
            DisasterResponseDto dto = new DisasterResponseDto();
            dto.setId(disaster.getId());
            dto.setName(disaster.getName());
            dto.setDescription(disaster.getDescription());
            dto.setDate(disaster.getDate());
            dto.setIsActive(disaster.getIsActive());
            if (disaster.getMainZone() != null) {
                dto.setCenter(disaster.getMainZone().getCentroid());
            }

            disasterDtos.add(dto);
        }

        return disasterDtos;

    }

    @Override
    public OneDisasterResponseDto getDisaster(Long id) {
        Disaster disaster = disasterRepository.findById(id).orElseThrow(
                DisasterNotFoundException::new
        );

        OneDisasterResponseDto oneDisasterResponseDto = new OneDisasterResponseDto();

        for (Zone zone : disaster.getZones() ){

            ZoneResponseDto zoneResponseDto = new ZoneResponseDto();
            zoneResponseDto.setName(zone.getName());
            zoneResponseDto.setId(zone.getId());
            zoneResponseDto.setGeometry(zone.getGeometry());

            oneDisasterResponseDto.getZones().add(zoneResponseDto);
        }

        oneDisasterResponseDto.setDescription(disaster.getDescription());
        oneDisasterResponseDto.setId(disaster.getId());
        oneDisasterResponseDto.setDate(disaster.getDate());
        oneDisasterResponseDto.setIsActive(disaster.getIsActive());
        oneDisasterResponseDto.setMainZone(disaster.getMainZone());

        return oneDisasterResponseDto;
    }

}
