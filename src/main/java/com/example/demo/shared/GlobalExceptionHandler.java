package com.example.demo.shared;

import com.example.demo.shared.exceptions.ApiException;
import com.example.demo.shared.exceptions.InvalidDOBException;
import com.example.demo.shared.exceptions.InvalidEmailFormatException;
import com.example.demo.shared.exceptions.InvalidNameException;
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
        errorMessage,
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
        errorMessage,
        "Message not readable",
        ZonedDateTime.now(ZoneId.of("Z"))
    );
    return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
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
        errorMessage,
        "Invalid Blank Or  Null Resource",
        ZonedDateTime.now(ZoneId.of("Z"))
    );
    return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
  }

  /*
  Handling invalid email exception.
   */
  @ExceptionHandler(InvalidEmailFormatException.class)
  public ResponseEntity<Object> handleInvalidEmailFormatException(InvalidEmailFormatException ex) {
    String errorMessage = ex.getMessage();
    ApiException apiException = new ApiException(
        errorMessage,
        "Invalid format",
        ZonedDateTime.now(ZoneId.of("Z"))
    );
    log.error(errorMessage);
    return new ResponseEntity<>(apiException, HttpStatus.UNPROCESSABLE_ENTITY);
  }

  /*
  Handling the invalid name exception.
   */
  @ExceptionHandler(InvalidNameException.class)
  public ResponseEntity<Object> handleInvalidNameException(InvalidNameException ex) {
    String errorMessage = ex.getMessage();
    ApiException apiException = new ApiException(
        errorMessage,
        "Invalid format",
        ZonedDateTime.now(ZoneId.of("Z"))
    );
    log.error(errorMessage);
    return new ResponseEntity<>(apiException, HttpStatus.UNPROCESSABLE_ENTITY);
  }

  /*
  Handling the invalid date of birth exception.
   */
  @ExceptionHandler(InvalidDOBException.class)
  public ResponseEntity<Object> handleInvalidDOBException(InvalidDOBException ex) {
    String errorMessage = ex.getMessage();
    ApiException apiException = new ApiException(
        errorMessage,
        "Invalid format",
        ZonedDateTime.now(ZoneId.of("Z"))
    );
    log.error(errorMessage);
    return new ResponseEntity<>(apiException, HttpStatus.UNPROCESSABLE_ENTITY);
  }


}