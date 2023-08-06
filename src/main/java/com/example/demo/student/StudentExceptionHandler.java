package com.example.demo.student;

import com.example.demo.student.exceptions.EmailAlreadyTakenException;
import com.example.demo.student.exceptions.InvalidDOBException;
import com.example.demo.student.exceptions.InvalidEmailFormatException;
import com.example.demo.student.exceptions.InvalidNameException;
import com.example.demo.student.exceptions.StudentNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * This class is for handling exceptions within the student package.
 */
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class StudentExceptionHandler {

  private static final Logger logger = LoggerFactory.getLogger(
      com.example.demo.student.StudentExceptionHandler.class);

  /*
  Handling the student not found exception.
   */
  @ExceptionHandler(StudentNotFoundException.class)
  public static ResponseEntity<String> handleStudentNotFoundException(StudentNotFoundException ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
  }

  /*
  Handling the email already taken exception.
   */
  @ExceptionHandler(EmailAlreadyTakenException.class)
  public static ResponseEntity<String> handleEmailAlreadyTakenException(
      EmailAlreadyTakenException ex) {
    return ResponseEntity.badRequest().body(ex.getMessage());
  }

  /*
  Handling the invalid email format exception.
   */
  @ExceptionHandler(InvalidEmailFormatException.class)
  public ResponseEntity<String> handleInvalidEmailFormatException(InvalidEmailFormatException ex) {
    return ResponseEntity.badRequest().body(ex.getMessage());
  }

  /*
  Handling the invalid name exception.
   */
  @ExceptionHandler(InvalidNameException.class)
  public ResponseEntity<String> handleInvalidNameException(InvalidNameException ex) {
    return ResponseEntity.badRequest().body(ex.getMessage());
  }

  /*
  Handling the invalid date of birth exception.
   */
  @ExceptionHandler(InvalidDOBException.class)
  public ResponseEntity<String> handleInvalidDOBException(InvalidDOBException ex) {
    return ResponseEntity.badRequest().body(ex.getMessage());
  }

  /*
  Handling the date of birth format exception.
   */
  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<String> handleHttpMessageNotReadableException
  (HttpMessageNotReadableException ex) {
    logger.error("Invalid DOB format. Date of Birth should be in the format: yyyy-MM-dd");
    return ResponseEntity.badRequest().
        body("\"Invalid DOB format. Date of Birth should be in the format: yyyy-MM-dd\")");


  }

}
