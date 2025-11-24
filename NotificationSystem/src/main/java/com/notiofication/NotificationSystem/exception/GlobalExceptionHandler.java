package com.notiofication.NotificationSystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserException.class)
    public ResponseEntity<ErrorDetails> userExceptionHandler(UserException e, WebRequest req){

        ErrorDetails me = new ErrorDetails();
        me.setTimeStamp(LocalDateTime.now());
        me.setMessage(e.getMessage());
        me.setDetails(req.getDescription(false));

        return new ResponseEntity<ErrorDetails>(me, HttpStatus.BAD_REQUEST);
    }
}
