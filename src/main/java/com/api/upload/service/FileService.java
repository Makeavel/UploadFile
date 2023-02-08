package com.api.upload.service;

import com.api.upload.model.FileDB;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.stream.Stream;

public interface FileService {

    public FileDB setSave(MultipartFile file) throws IOException;

    public FileDB getFile(String id);

    public Stream<FileDB> getAllFiles();
}
