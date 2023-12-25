package com.example.disaster_service.service;

import com.example.disaster_service.dto.DeclarationDto;
import com.example.disaster_service.model.Declaration;

import java.util.List;

public interface DeclarationService {

    public Declaration createDeclaration(DeclarationDto declarationDto);

    public Declaration getDeclaration(Long id);

    public List<Declaration> getAllDeclarations();

    public Declaration archiveDeclaration(Long id);

}
