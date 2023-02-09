package com.api.upload.controller;

import com.api.upload.model.FileDB;
import com.api.upload.model.Menssage;
import com.api.upload.model.ResponseFile;
import com.api.upload.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class FileController {

    private final FileService service;

    @GetMapping("/files")
    public ResponseEntity<List<ResponseFile>> getListFiles(){
        List<ResponseFile> files = service.getAllFiles().map(dbFile -> {
            String fileDownloadUrl = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/files/")
                    .path(dbFile.getId())
                    .toUriString();

            return new ResponseFile(dbFile.getId(), dbFile.getName(),fileDownloadUrl, dbFile.getType(),dbFile.getData().length);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @GetMapping("/files/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable String id) {
        FileDB fileDB = service.getFile(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName() + "\"")
                .body(fileDB.getData());
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
