package com.example.disaster_service.serviceImpl;

import com.example.disaster_service.dto.DeclarationDto;
import com.example.disaster_service.dto.PointDto;
import com.example.disaster_service.exeception.DeclarationException;
import com.example.disaster_service.model.Declaration;
import com.example.disaster_service.repository.DeclarationRepository;
import com.example.disaster_service.service.DeclarationService;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeclarationServiceImpl implements DeclarationService {

    @Autowired
    DeclarationRepository declarationRepository;

    @Override
    public Declaration createDeclaration(DeclarationDto declarationDto) {
        Declaration declaration = new Declaration();

        PointDto pointDto = declarationDto.getLocalisation();
        GeometryFactory geometryFactory = new GeometryFactory();
        Point point = geometryFactory.createPoint(new Coordinate(pointDto.getLongitude(), pointDto.getLatitude()));

        declaration.setLocalisation(point);

        declaration.setFullName(declarationDto.getFullName());
        declaration.setPhoneNumber(declarationDto.getPhoneNumber());
        declaration.setAddress(declarationDto.getAddress());
        declaration.setDescription(declarationDto.getDescription());
        declaration.setIsArchived(false); // Assuming default is not archived

        return declarationRepository.save(declaration);
    }

    @Override
    public Declaration getDeclaration(Long id) {

        return declarationRepository.findById(id).orElseThrow(
                DeclarationException::new
        );

    }

    @Override
    public List<Declaration> getAllDeclarations() {
        return declarationRepository.findAll();
    }

    @Override
    public Declaration archiveDeclaration(Long id) {
        Declaration declaration = this.getDeclaration(id);
        declaration.setIsArchived(true);
        declarationRepository.save(declaration);
        return declaration;
    }

}
