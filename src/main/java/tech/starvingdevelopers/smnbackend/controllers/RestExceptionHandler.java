package tech.starvingdevelopers.smnbackend.controllers;

import org.hibernate.PropertyValueException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tech.starvingdevelopers.smnbackend.exceptions.GeneralException;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(GeneralException.class)
    public ProblemDetail handlerException(GeneralException exception) {
        return exception.toProblemDetail();
    }

    @ExceptionHandler(PropertyValueException.class)
    public ProblemDetail handlerException(PropertyValueException exception) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle("Invalid Parameter");
        problemDetail.setDetail(exception.getMessage());
        return problemDetail;
    }
}
