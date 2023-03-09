package com.ssafy.woonana.error.handler;

import com.ssafy.woonana.error.exception.ErrorResponse;
import com.ssafy.woonana.error.exception.custom.ParticipationDuplicateException;
import com.ssafy.woonana.error.exception.custom.ParticipationIsFullException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler (ParticipationDuplicateException.class)
    public ErrorResponse handleAlreadyExistParticipation(ParticipationDuplicateException ex) {
        return ErrorResponse.of(HttpStatus.CONFLICT, ex.getMessage());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler (ParticipationIsFullException.class)
    public ErrorResponse handleFullParticipation(ParticipationIsFullException ex) {
        return ErrorResponse.of(HttpStatus.CONFLICT, ex.getMessage());
    }
}