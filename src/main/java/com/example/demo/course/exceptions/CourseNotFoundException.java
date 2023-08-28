package com.example.demo.course.exceptions;

/**
 * This class represents the course not found exception.
 */
public class CourseNotFoundException extends RuntimeException {

  /*
  Exception's constructor.
   */
  public CourseNotFoundException(String message) {
    super(message);
  }
}



