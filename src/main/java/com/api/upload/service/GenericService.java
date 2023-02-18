package com.api.upload.service;

import com.api.upload.model.Generic;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GenericService {


    Generic setService(String generic, UUID id);
    
    List<Generic> getReadAll();

    Optional<Generic> getReadId(UUID id);
}
