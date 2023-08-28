package com.example.demo.distribution.exceptions;

/**
 * This class represents the maximum number of courses reached for a teacher exception.
 */
public class MaximumNumberOfCoursesReachedException extends RuntimeException {

  /*
  Exception's constructor.
   */
  public MaximumNumberOfCoursesReachedException(String message) {
    super(message);
  }
}



