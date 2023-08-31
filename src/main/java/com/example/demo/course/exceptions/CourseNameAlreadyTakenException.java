package com.example.demo.course.exceptions;

/**
 * This class represents the already existing course name exception..
 */
public class CourseNameAlreadyTakenException extends IllegalStateException {

  /*
  Exception's constructor.
   */
  public CourseNameAlreadyTakenException(String message) {
    super(message);
  }
}