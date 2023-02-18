package com.api.upload.service;

import com.api.upload.model.FileDB;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;
import java.util.stream.Stream;

public interface FileService {

    public FileDB setSave(MultipartFile file) throws IOException;

    public FileDB getFile(UUID id);

    InputStream decoderString(FileDB file) throws IOException;

    public Stream<FileDB> getAllFiles();
}
