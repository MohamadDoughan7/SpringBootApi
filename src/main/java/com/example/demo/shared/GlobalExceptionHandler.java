package com.example.demo.shared;

import com.example.demo.course.enrollment.exceptions.EnrollmentNotFoundException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * This class is for handling exceptions across all the application's classes.
 */
@ControllerAdvice
@Log4j2
public class GlobalExceptionHandler {

  /*
   Handling the general exceptions.
  */
  @ExceptionHandler(Exception.class)
  public ResponseEntity<Object> handleGenericException(Exception ex) {
    String errorMessage = ex.getMessage();
    log.error(errorMessage);
    ApiException apiException = new ApiException(
        "Generic Error Check with the Support.",
        "Generic error",
        ZonedDateTime.now(ZoneId.of("Z"))
    );
    return new ResponseEntity<>(apiException, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  /*
  Handling the httpMessageNotReadableException.
   */
  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<Object> handleHttpMessageNotReadableException
  (HttpMessageNotReadableException ex) {
    String errorMessage = ex.getMessage();
    log.error(errorMessage);
    ApiException apiException = new ApiException(
        "Message not readable",
        "Input error",
        ZonedDateTime.now(ZoneId.of("Z"))
    );
    return new ResponseEntity<>(apiException, HttpStatus.UNPROCESSABLE_ENTITY);
  }

  /*
  Handling the MethodArgumentNotValidException.
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Object> handleMethodArgumentNotValidException
  (MethodArgumentNotValidException ex) {
    String errorMessage = ex.getMessage();
    log.error(errorMessage);
    ApiException apiException = new ApiException(
        "Invalid Blank Or  Null Resource",
        "Validation Error",
        ZonedDateTime.now(ZoneId.of("Z"))
    );
    return new ResponseEntity<>(apiException, HttpStatus.UNPROCESSABLE_ENTITY);
  }

  /*
  Handling the enrollment not found exception.
   */
  @ExceptionHandler(EnrollmentNotFoundException.class)
  public ResponseEntity<Object> handleEnrollmentNotFoundException(EnrollmentNotFoundException ex) {
    String errorMessage = ex.getMessage();
    ApiException apiException = new ApiException(
        errorMessage,
        "Non Existing resource",
        ZonedDateTime.now(ZoneId.of("Z"))
    );
    log.error(errorMessage);
    return new ResponseEntity<>(apiException, HttpStatus.UNPROCESSABLE_ENTITY);
  }
}