package com.example.demo.course;

import com.example.demo.course.exceptions.CourseNameAlreadyTakenException;
import com.example.demo.course.exceptions.CourseNotFoundException;
import com.example.demo.shared.ApiException;
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
 * This class is for handling exceptions within the course package.
 */
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
@Log4j2
public class CourseExceptionHandler {

  /*
  Handling the course not found exception.
   */
  @ExceptionHandler(CourseNotFoundException.class)
  public ResponseEntity<Object> handleCourseNotFoundException(CourseNotFoundException ex) {
    String errorMessage = ex.getMessage();
    ApiException apiException = new ApiException(
        errorMessage,
        "Non Existing resource",
        ZonedDateTime.now(ZoneId.of("Z"))
    );
    log.warn(errorMessage);
    return new ResponseEntity<>(apiException, HttpStatus.UNPROCESSABLE_ENTITY);
  }

  /*
  Handling the course name already taken exception.
   */
  @ExceptionHandler(CourseNameAlreadyTakenException.class)
  public ResponseEntity<Object> handleCourseNameTakenException(
      CourseNameAlreadyTakenException ex) {
    String errorMessage = ex.getMessage();
    ApiException apiException = new ApiException(
        errorMessage,
        "Duplicate resource",
        ZonedDateTime.now(ZoneId.of("Z"))
    );
    log.warn(errorMessage);
    return new ResponseEntity<>(apiException, HttpStatus.UNPROCESSABLE_ENTITY);
  }
}