package com.example.demo.teacher;

import com.example.demo.shared.ApiException;
import com.example.demo.teacher.exceptions.TeacherEmailAlreadyTakenException;
import com.example.demo.teacher.exceptions.TeacherNotFoundException;
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
 * This class is for handling exceptions within the student package.
 */
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
@Log4j2
public class TeacherExceptionHandler {

  /*
  Handling the teacher not found exception.
   */
  @ExceptionHandler(TeacherNotFoundException.class)
  public ResponseEntity<Object> handleTeacherNotFoundException(TeacherNotFoundException ex) {
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
  Handling the teacher email already taken exception.
   */
  @ExceptionHandler(TeacherEmailAlreadyTakenException.class)
  public ResponseEntity<Object> handleTeacherEmailAlreadyTakenException(
      TeacherEmailAlreadyTakenException ex) {
    String errorMessage = ex.getMessage();
    ApiException apiException = new ApiException(
        errorMessage,
        "Duplicate resource",
        ZonedDateTime.now(ZoneId.of("Z"))
    );
    log.error(errorMessage);
    return new ResponseEntity<>(apiException, HttpStatus.UNPROCESSABLE_ENTITY);
  }
}