package com.api.upload.repository;

import com.api.upload.model.Generic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GenericRepository extends JpaRepository<Generic, UUID> {
}
