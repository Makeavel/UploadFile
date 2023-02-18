package com.api.upload.controller;

import com.api.upload.model.FileDB;
import com.api.upload.model.Generic;
import com.api.upload.model.Menssage;
import com.api.upload.service.FileService;
import com.api.upload.service.GenericService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/generic")
public class GenericController {

    private final FileService fileService;
    private final GenericService genericService;

    // -------------------------- CREATE + FILE ---------------
    
    @PostMapping("/create")
    public ResponseEntity<Menssage> uploadAll(@RequestParam("file") MultipartFile file ,
                                              @RequestParam("generic") String generic) throws Exception{

        FileDB fileDB;
        String message = "";
        try{

            if(file != null || generic != null){

                fileDB = fileService.setSave((MultipartFile) file);
                genericService.setService(generic , fileDB.getId());

            }
            message = "[SUCCESS] upload realizado com sucesso" ;
            return ResponseEntity.status(HttpStatus.OK).body(new Menssage(message));

        }catch (Exception ex){
            message = "[ERROR] upload não realizado" ;
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new Menssage(message));
        }
    }
    
     // -------------------------- READ ---------------

    @GetMapping("/readAll")
    public List<Generic> readAll(){
        return genericService.getReadAll();
    }

    @GetMapping("/read/{id}")
    public Optional<Generic> readAll(@PathVariable("id") UUID id){
        return genericService.getReadId(id);
    }
}
