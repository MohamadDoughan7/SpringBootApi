package com.example.demo.student;

import com.example.demo.shared.ApiException;
import com.example.demo.student.exceptions.StudentEmailAlreadyTakenException;
import com.example.demo.student.exceptions.StudentNotFoundException;
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
public class StudentExceptionHandler {

  /*
  Handling the student not found exception.
   */
  @ExceptionHandler(StudentNotFoundException.class)
  public ResponseEntity<Object> handleStudentNotFoundException(StudentNotFoundException ex) {
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
  Handling the email already taken exception.
   */
  @ExceptionHandler(StudentEmailAlreadyTakenException.class)
  public ResponseEntity<Object> handleEmailAlreadyTakenException(
      StudentEmailAlreadyTakenException ex) {
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