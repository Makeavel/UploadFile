package com.api.upload.exception;

import com.api.upload.model.Menssage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class FileUploadExceptionAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<Menssage> handleMaxSizeException(MaxUploadSizeExceededException excp){
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new Menssage("Arquivo muito grande"));
    }
}
