package com.example.demo.course.teachercourse;

import com.example.demo.course.teachercourse.exceptions.TecaherCourseRelationAlreadyExistsException;
import com.example.demo.course.teachercourse.exceptions.TeacherCourseRelationNotFoundException;
import com.example.demo.course.teachercourse.exceptions.MaximumNumberOfCoursesReachedException;
import com.example.demo.shared.ApiException;
import com.example.demo.course.teachercourse.exceptions.MaximumNumberOfTeachersReachedException;
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
 * This class is for handling exceptions within the distribution package.
 */
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
@Log4j2
public class TeacherCourseExceptionHandler {

  /*
  Handling the distribution not found exception.
   */
  @ExceptionHandler(TeacherCourseRelationNotFoundException.class)
  public ResponseEntity<Object> handleDistributionNotFoundException
  (TeacherCourseRelationNotFoundException ex) {
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
  Handling the distribution already exists exception.
   */
  @ExceptionHandler(TecaherCourseRelationAlreadyExistsException.class)
  public ResponseEntity<Object> handleDistributionAlreadyExistsException(
      TecaherCourseRelationAlreadyExistsException ex) {
    String errorMessage = ex.getMessage();
    ApiException apiException = new ApiException(
        errorMessage,
        "Already Existing resource",
        ZonedDateTime.now(ZoneId.of("Z"))
    );
    log.error(errorMessage);
    return new ResponseEntity<>(apiException, HttpStatus.UNPROCESSABLE_ENTITY);
  }

  /*
   Handling the maximum number of courses reached exception.
   */
  @ExceptionHandler(MaximumNumberOfCoursesReachedException.class)
  public ResponseEntity<Object> handleMaximumNumberOfCoursesReachedException(
      MaximumNumberOfCoursesReachedException ex) {
    String errorMessage = ex.getMessage();
    ApiException apiException = new ApiException(
        errorMessage,
        "Maximum resource reached",
        ZonedDateTime.now(ZoneId.of("Z"))
    );
    log.error(errorMessage);
    return new ResponseEntity<>(apiException, HttpStatus.UNPROCESSABLE_ENTITY);
  }

  /*
   Handling the maximum number of teachers reached exception.
   */
  @ExceptionHandler(MaximumNumberOfTeachersReachedException.class)
  public ResponseEntity<Object> handleMaximumNumberOfTeachersReachedException(
      MaximumNumberOfTeachersReachedException ex) {
    String errorMessage = ex.getMessage();
    ApiException apiException = new ApiException(
        errorMessage,
        "Maximum resource reached",
        ZonedDateTime.now(ZoneId.of("Z"))
    );
    log.error(errorMessage);
    return new ResponseEntity<>(apiException, HttpStatus.UNPROCESSABLE_ENTITY);
  }
}