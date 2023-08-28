package com.example.demo.distribution;

import com.example.demo.shared.exceptions.ApiException;
import com.example.demo.distribution.exceptions.MaximumNumberOfCoursesReachedException;
import com.example.demo.distribution.exceptions.MaximumNumberOfTeachersReachedException;
import com.example.demo.distribution.exceptions.DistributionAlreadyExistsException;
import com.example.demo.distribution.exceptions.DistributionNotFoundException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * This class is for handling exceptions within the distribution package.
 */
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
@Log4j2
public class DistributionExceptionHandler {

  /*
  Handling the distribution not found exception.
   */
  @ExceptionHandler(DistributionNotFoundException.class)
  public ResponseEntity<Object> handleDistributionNotFoundException
  (DistributionNotFoundException ex) {
    String errorMessage = ex.getMessage();
    ApiException apiException = new ApiException(
        errorMessage,
        "Non Existing resource",
        ZonedDateTime.now(ZoneId.of("Z"))
    );
    log.error(errorMessage);
    return new ResponseEntity<>(apiException, HttpStatus.UNPROCESSABLE_ENTITY);
  }

  /*
  Handling the distribution already exists exception.
   */
  @ExceptionHandler(DistributionAlreadyExistsException.class)
  public ResponseEntity<Object> handleDistributionAlreadyExistsException(
      DistributionAlreadyExistsException ex) {
    String errorMessage = ex.getMessage();
    ApiException apiException = new ApiException(
        errorMessage,
        "Already Existing resource",
        ZonedDateTime.now(ZoneId.of("Z"))
    );
    log.error(errorMessage);
    return new ResponseEntity<>(apiException, HttpStatus.UNPROCESSABLE_ENTITY);
  }

  /*
   Handling the maximum number of courses reached exception.
   */
  @ExceptionHandler(MaximumNumberOfCoursesReachedException.class)
  public ResponseEntity<Object> handleMaximumNumberOfCoursesReachedException(
      MaximumNumberOfCoursesReachedException ex) {
    String errorMessage = ex.getMessage();
    ApiException apiException = new ApiException(
        errorMessage,
        "Maximum resource reached",
        ZonedDateTime.now(ZoneId.of("Z"))
    );
    log.error(errorMessage);
    return new ResponseEntity<>(apiException, HttpStatus.UNPROCESSABLE_ENTITY);
  }

  /*
   Handling the maximum number of teachers reached exception.
   */
  @ExceptionHandler(MaximumNumberOfTeachersReachedException.class)
  public ResponseEntity<Object> handleMaximumNumberOfTeachersReachedException(
      MaximumNumberOfTeachersReachedException ex) {
    String errorMessage = ex.getMessage();
    ApiException apiException = new ApiException(
        errorMessage,
        "Maximum resource reached",
        ZonedDateTime.now(ZoneId.of("Z"))
    );
    log.error(errorMessage);
    return new ResponseEntity<>(apiException, HttpStatus.UNPROCESSABLE_ENTITY);
  }

}