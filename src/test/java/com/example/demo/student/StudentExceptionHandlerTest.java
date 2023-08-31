package com.example.demo.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.example.demo.shared.ApiException;
import com.example.demo.student.exceptions.StudentEmailAlreadyTakenException;
import com.example.demo.student.exceptions.StudentNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * This class is for testing the StudentExceptionHandler class.
 */
public class StudentExceptionHandlerTest {

  private StudentExceptionHandler studentExceptionHandler;

  @Mock
  private StudentNotFoundException studentNotFoundException;

  @Mock
  private StudentEmailAlreadyTakenException studentEmailAlreadyTakenException;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    studentExceptionHandler = new StudentExceptionHandler();
  }

  /**
   * Testing the handling of studentNotFoundException.
   */
  @Test
  public void testHandleStudentNotFoundException() {
    String errorMessage = "Student not found";
    when(studentNotFoundException.getMessage()).thenReturn(errorMessage);

    ResponseEntity<Object> responseEntity = studentExceptionHandler.handleStudentNotFoundException(
        studentNotFoundException);

    assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, responseEntity.getStatusCode());
    ApiException apiException = (ApiException) responseEntity.getBody();
    assert apiException != null;
    assertEquals(errorMessage, apiException.getMessage());
    assertEquals("Non Existing resource", apiException.getStatus());
  }

  /**
   * Testing the handling of email already taken exception.
   */
  @Test
  public void testHandleEmailAlreadyTakenException() {
    String errorMessage = "Email already taken";
    when(studentEmailAlreadyTakenException.getMessage()).thenReturn(errorMessage);

    ResponseEntity<Object> responseEntity = studentExceptionHandler.handleEmailAlreadyTakenException(
        studentEmailAlreadyTakenException);

    assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, responseEntity.getStatusCode());
    ApiException apiException = (ApiException) responseEntity.getBody();
    assert apiException != null;
    assertEquals(errorMessage, apiException.getMessage());
    assertEquals("Duplicate resource", apiException.getStatus());
  }
}