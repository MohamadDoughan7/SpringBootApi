package com.example.demo.enrollment.exceptions;

/**
 * This class represents the enrollment exceeds course capacity exception.
 */
public class CourseCapacityExceededException extends RuntimeException {

  /*
  Exception's constructor.
   */
  public CourseCapacityExceededException(String message) {
    super(message);
  }
}



