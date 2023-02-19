package com.api.upload.controller;

import com.api.upload.model.FileDB;
import com.api.upload.model.Menssage;
import com.api.upload.model.ResponseFile;
import com.api.upload.service.FileService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/file")
@RequiredArgsConstructor
public class FileController {

    private final FileService service;

    @GetMapping("/files")
    public ResponseEntity<List<FileDB>> getListFiles(){
        List<FileDB> files = service.getAllFiles();

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @GetMapping("/files/{id}")
    public ResponseEntity<FileDB> getFile(@PathVariable UUID id) throws IOException {
        FileDB fileDB = service.getFile(id);
        return ResponseEntity.ok().body(fileDB);
    }

    @PostMapping("/upload")
    public ResponseEntity<Menssage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            service.setSave(file);
            message = "[SUCCESS] upload do arquivo " + file.getOriginalFilename() + " realizado com sucesso";
            return ResponseEntity.status(HttpStatus.OK).body(new Menssage(message));
        }catch (Exception ex) {
            message = "[ERROR] upload do arquivo " + file.getOriginalFilename() ;
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new Menssage(message));
        }
    }
}
