package com.api.upload.controller;

import com.api.upload.model.FileDB;
import com.api.upload.model.Menssage;
import com.api.upload.service.FileService;
import com.api.upload.service.GenericService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/generic")
public class GenericController {

    private final FileService fileService;
    private final GenericService genericService;

    @PostMapping("/create")
    public ResponseEntity<Menssage> uploadAll(@RequestParam MultipartFile file ,
                                              @RequestParam String generic) throws Exception{

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
            return ResponseEntity.status(HttpStatus.OK).body(new Menssage(message));
        }
    }
}
