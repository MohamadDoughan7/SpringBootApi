package com.example.demo.shared;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.example.demo.course.enrollment.exceptions.EnrollmentNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;

/**
 * This class is foe testing the global exception handler
 */
public class GlobalExceptionHandlerTest {

  private GlobalExceptionHandler globalExceptionHandler;

  @Mock
  private Exception genericException;

  @Mock
  private HttpMessageNotReadableException httpMessageNotReadableException;

  @Mock
  private MethodArgumentNotValidException methodArgumentNotValidException;

  @Mock
  private EnrollmentNotFoundException enrollmentNotFoundException;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    globalExceptionHandler = new GlobalExceptionHandler();
  }

  /**
   * Testing the handling of the generic exceptions
   */
  @Test
  public void testHandleGenericException() {
    String errorMessage = "Generic error message";
    when(genericException.getMessage()).thenReturn(errorMessage);

    ResponseEntity<Object> responseEntity =
        globalExceptionHandler.handleGenericException(genericException);

    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    ApiException apiException = (ApiException) responseEntity.getBody();
    assert apiException != null;
    assertEquals("Generic Error Check with the Support.", apiException.getMessage());
    assertEquals("Generic error", apiException.getStatus());
  }

  /**
   * Testing the handling of the HttpMessageNotReadableException
   */
  @Test
  public void testHandleHttpMessageNotReadableException() {
    String errorMessage = "Message not readable";
    when(httpMessageNotReadableException.getMessage()).thenReturn(errorMessage);

    ResponseEntity<Object> responseEntity = globalExceptionHandler.handleHttpMessageNotReadableException(
        httpMessageNotReadableException);

    assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, responseEntity.getStatusCode());
    ApiException apiException = (ApiException) responseEntity.getBody();
    assert apiException != null;
    assertEquals("Message not readable", apiException.getMessage());
    assertEquals("Input error", apiException.getStatus());
  }

  /**
   * Testing the handling of the MethodArgumentNotValidException
   */
  @Test
  public void testHandleMethodArgumentNotValidException() {
    String errorMessage = "Invalid Blank Or Null Resource";
    when(methodArgumentNotValidException.getMessage()).thenReturn(errorMessage);

    ResponseEntity<Object> responseEntity = globalExceptionHandler
        .handleMethodArgumentNotValidException(methodArgumentNotValidException);

    assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, responseEntity.getStatusCode());
    ApiException apiException = (ApiException) responseEntity.getBody();
    assert apiException != null;
    assertEquals("Invalid Blank Or  Null Resource", apiException.getMessage());
    assertEquals("Validation Error", apiException.getStatus());
  }

  /**
   * Testing ethe handling of enrollment not found exception
   */
  @Test
  public void testHandleEnrollmentNotFoundException() {
    String errorMessage = "Enrollment not found";
    when(enrollmentNotFoundException.getMessage()).thenReturn(errorMessage);

    ResponseEntity<Object> responseEntity =
        globalExceptionHandler.handleEnrollmentNotFoundException(enrollmentNotFoundException);

    assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, responseEntity.getStatusCode());
    ApiException apiException = (ApiException) responseEntity.getBody();
    assert apiException != null;
    assertEquals(errorMessage, apiException.getMessage());
    assertEquals("Non Existing resource", apiException.getStatus());
  }
}