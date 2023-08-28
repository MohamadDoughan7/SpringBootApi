package com.example.demo.enrollment;

import com.example.demo.enrollment.exceptions.CourseCapacityExceededException;
import com.example.demo.enrollment.exceptions.EnrollmentAlreadyExistsException;
import com.example.demo.enrollment.exceptions.EnrollmentNotFoundException;
import com.example.demo.enrollment.exceptions.MaximumEnrollmentReachedException;
import com.example.demo.shared.exceptions.ApiException;
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
 * This class is for handling exceptions within the enrollment package.
 */
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
@Log4j2
public class EnrollmentExceptionHandler {

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

  /*
  Handling the enrollment already exists exception.
   */
  @ExceptionHandler(EnrollmentAlreadyExistsException.class)
  public ResponseEntity<Object> handleEnrollmentAlreadyExistsException(
      EnrollmentAlreadyExistsException ex) {
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
  Handling the course capacity exceeded exception.
   */
  @ExceptionHandler(CourseCapacityExceededException.class)
  public ResponseEntity<Object> handleCourseCapacityExceededException(
      CourseCapacityExceededException ex) {
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
  Handling the maximum enrollments reached exception.
   */
  @ExceptionHandler(MaximumEnrollmentReachedException.class)
  public ResponseEntity<Object> handleMaximumEnrollmentReachedException(
      MaximumEnrollmentReachedException ex) {
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