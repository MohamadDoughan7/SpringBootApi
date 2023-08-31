package com.example.demo.teacher;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.demo.shared.ApiException;
import com.example.demo.teacher.exceptions.TeacherEmailAlreadyTakenException;
import com.example.demo.teacher.exceptions.TeacherNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Testing thTeacherExceptionHandler
 */
public class TeacherExceptionHandlerTest {

  private final TeacherExceptionHandler teacherExceptionHandler = new TeacherExceptionHandler();

  /**
   * Testing the handling of teacher not found exception.
   */
  @Test
  public void testHandleTeacherNotFoundException() {
    /*
     Create a mock exception
     */
    TeacherNotFoundException ex = new TeacherNotFoundException("Teacher not found");

    /*
     Call the handler method
     */
    ResponseEntity<Object> responseEntity = teacherExceptionHandler.handleTeacherNotFoundException(
        ex);

    /*
     Check the response status code
     */
    assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, responseEntity.getStatusCode());

    /*
     Check the response body
     */
    ApiException apiException = (ApiException) responseEntity.getBody();
    assert apiException != null;
    assertEquals("Teacher not found", apiException.getMessage());
    assertEquals("Non Existing resource", apiException.getStatus());
  }

  /**
   * Testing the handling of the TeacherEmailAlreadyTakenException.
   */
  @Test
  public void testHandleTeacherEmailAlreadyTakenException() {
    /*
     Create a mock exception
     */
    TeacherEmailAlreadyTakenException ex = new TeacherEmailAlreadyTakenException(
        "Email already taken");

    /*
     Call the handler method
     */
    ResponseEntity<Object> responseEntity = teacherExceptionHandler.handleTeacherEmailAlreadyTakenException(
        ex);

    /*
     Check the response status code
     */
    assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, responseEntity.getStatusCode());

    /*
     Check the response body
     */
    ApiException apiException = (ApiException) responseEntity.getBody();
    assert apiException != null;
    assertEquals("Email already taken", apiException.getMessage());
    assertEquals("Duplicate resource", apiException.getStatus());
  }
}