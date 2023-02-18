package com.api.upload.service;

import com.api.upload.model.FileDB;
import com.api.upload.repository.FileRepository;
import lombok.RequiredArgsConstructor;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.UUID;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService{

    private final FileRepository repository;


    @Override
    public FileDB setSave(MultipartFile file) throws IOException {
        String fileString = Base64.getEncoder().encodeToString(file.getBytes());
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        FileDB fileDB = new FileDB(fileName, file.getContentType(), fileString);

        return repository.save(fileDB);
    }

    @Override
    public FileDB getFile(UUID id) {
        return repository.findById(id).get();
    }

    @Override
    public InputStream decoderString(FileDB file) throws IOException {

        byte[] decodeBase64 = Base64.getDecoder().decode(file.getBase64().getBytes(StandardCharsets.UTF_8));
        //File panquecaCCaramelo = new File("pcc");
        //FileUtils.writeByteArrayToFile(panquecaCCaramelo, decodeBase64);

        InputStream is = new ByteArrayInputStream(decodeBase64);

        return is;
    }

    @Override
    public Stream<FileDB> getAllFiles() {
        return repository.findAll().stream();
    }

}

