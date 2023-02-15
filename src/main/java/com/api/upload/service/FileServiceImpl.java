package com.api.upload.service;

import com.api.upload.model.FileDB;
import com.api.upload.repository.FileRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService{

    private final FileRepository repository;


    @Override
    public FileDB setSave(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        FileDB fileDB = new FileDB(fileName, file.getContentType(), file.getBytes());

        return repository.save(fileDB);
    }

    @Override
    public FileDB getFile(UUID id) {
        return repository.findById(id).get();
    }

    @Override
    public Stream<FileDB> getAllFiles() {
        return repository.findAll().stream();
    }

}

