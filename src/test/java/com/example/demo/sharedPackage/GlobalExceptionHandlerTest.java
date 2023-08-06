package com.example.demo.sharedPackage;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * This class is for testing the GlobalExceptionHandler class.
 */
public class GlobalExceptionHandlerTest {

  private final GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();


  /**
   * Testing the handling of the general Exceptions.
   */
  @Test
  public void handleGenericException() {
    ResponseEntity<String> responseEntity = globalExceptionHandler.
        handleGenericException(new Exception("Something went wrong"));
    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    assertEquals("Oops! Something went wrong.", responseEntity.getBody());
  }
}
