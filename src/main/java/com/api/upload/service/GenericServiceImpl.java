package com.api.upload.service;

import com.api.upload.model.FileDB;
import com.api.upload.model.Generic;
import com.api.upload.repository.GenericRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GenericServiceImpl implements GenericService{

    private final GenericRepository genericRepository;

    @Override
    public Generic setService(String generic, UUID id) {

        FileDB fileDb = new FileDB();
        Generic entityGeneric ;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            entityGeneric = objectMapper.readValue(generic, Generic.class);
            fileDb.setId(id);
            entityGeneric.setFile(fileDb);
            return genericRepository.save(entityGeneric);

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    @Override
    public List<Generic> getReadAll() {
        return genericRepository.findAll();
    }

    @Override
    public Optional<Generic> getReadId(UUID id) {
        return genericRepository.findById(id);
    }
}
