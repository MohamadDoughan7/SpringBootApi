package com.example.demo.course;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.demo.course.exceptions.CourseNameAlreadyTakenException;
import com.example.demo.course.exceptions.CourseNotFoundException;
import com.example.demo.shared.ApiException;
import java.time.ZoneId;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * This class is for testing the courseExceptionHandler
 */
public class CourseExceptionHandlerTest {

  /**
   * Testing the handling of the CourseNotFoundException
   */
  @Test
  void testHandleCourseNotFoundException() {
    CourseExceptionHandler exceptionHandler = new CourseExceptionHandler();
    CourseNotFoundException exception = new CourseNotFoundException("Course not found");

    ResponseEntity<Object> responseEntity =
        exceptionHandler.handleCourseNotFoundException(exception);

    assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, responseEntity.getStatusCode());
    ApiException apiException = (ApiException) responseEntity.getBody();
    assert apiException != null;
    assertEquals("Course not found", apiException.getMessage());
    assertEquals("Non Existing resource", apiException.getStatus());
    assertEquals(ZoneId.of("Z"), apiException.getTimeStamp().getZone());
  }

  /**
   * Testing the handling of the course name already taken exception
   */
  @Test
  void testHandleCourseNameTakenException() {
    CourseExceptionHandler exceptionHandler = new CourseExceptionHandler();
    CourseNameAlreadyTakenException exception =
        new CourseNameAlreadyTakenException("Course name already taken");

    ResponseEntity<Object> responseEntity =
        exceptionHandler.handleCourseNameTakenException(exception);

    assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, responseEntity.getStatusCode());
    ApiException apiException = (ApiException) responseEntity.getBody();
    assert apiException != null;
    assertEquals("Course name already taken", apiException.getMessage());
    assertEquals("Duplicate resource", apiException.getStatus());
    assertEquals(ZoneId.of("Z"), apiException.getTimeStamp().getZone());
  }
}