package com.example.demo.student;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.demo.student.exceptions.EmailAlreadyTakenException;
import com.example.demo.student.exceptions.InvalidDOBException;
import com.example.demo.student.exceptions.InvalidEmailFormatException;
import com.example.demo.student.exceptions.InvalidNameException;
import com.example.demo.student.exceptions.StudentNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;

/**
 * This class is for testing the StudentExceptionHandler class.
 */
public class StudentExceptionHandlerTest {

  private final StudentExceptionHandler studentExceptionHandler = new StudentExceptionHandler();

  /**
   * Testing the handling of StudentNotFoundException.
   */
  @Test
  public void handleStudentNotFoundException() {
    ResponseEntity<String> responseEntity = StudentExceptionHandler.handleStudentNotFoundException
        (new StudentNotFoundException("Student not found"));
    assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    assertEquals("Student not found", responseEntity.getBody());
  }

  /**
   * Testing the handling of EmailAlreadyTakenException.
   */
  @Test
  public void handleEmailAlreadyTakenException() {
    ResponseEntity<String> responseEntity = StudentExceptionHandler.handleEmailAlreadyTakenException
        (new EmailAlreadyTakenException("Email already taken"));
    assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    assertEquals("Email already taken", responseEntity.getBody());
  }

  /**
   * Testing the handling of InvalidEmailFormatException.
   */
  @Test
  public void handleInvalidEmailFormatException() {
    ResponseEntity<String> responseEntity = studentExceptionHandler.handleInvalidEmailFormatException
        (new InvalidEmailFormatException("Invalid email format"));
    assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    assertEquals("Invalid email format", responseEntity.getBody());
  }

  /**
   * Testing the handling of InvalidNameException.
   */
  @Test
  public void handleInvalidNameException() {
    ResponseEntity<String> responseEntity = studentExceptionHandler.handleInvalidNameException
        (new InvalidNameException("Invalid name"));
    assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    assertEquals("Invalid name", responseEntity.getBody());
  }

  /**
   * Testing the handling of InvalidDOBException.
   */
  @Test
  public void handleInvalidDOBException() {
    ResponseEntity<String> responseEntity = studentExceptionHandler.handleInvalidDOBException
        (new InvalidDOBException("Invalid DOB"));
    assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    assertEquals("Invalid DOB", responseEntity.getBody());
  }

  /**
   * Testing the handling of handleHttpMessageNotReadableException.
   */
  @Test
  public void handleHttpMessageNotReadableException() {
    ResponseEntity<String> responseEntity = studentExceptionHandler.
        handleHttpMessageNotReadableException(new HttpMessageNotReadableException("Invalid JSON"));
    assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    assertEquals
        ("\"Invalid DOB format. Date of Birth should be in the format: yyyy-MM-dd\")"
            , responseEntity.getBody());
  }

}
