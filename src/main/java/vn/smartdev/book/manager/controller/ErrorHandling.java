package vn.smartdev.book.manager.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@ControllerAdvice
public class ErrorHandling {

    static final Log log = LogFactory.getFactory().getInstance(ErrorHandling.class);

    @ExceptionHandler(IOException.class)
    @ResponseBody
    public ResponseEntity<?> handleIOException(IOException ex){
        log.error("IOException..");
        log.error("IOException", ex);
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
