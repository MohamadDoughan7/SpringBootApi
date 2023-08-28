package com.example.demo.enrollment.exceptions;

/**
 * This class represents the maximum enrollment reached exception.
 */
public class MaximumEnrollmentReachedException extends RuntimeException {

  /*
  Exception's constructor.
   */
  public MaximumEnrollmentReachedException(String message) {
    super(message);
  }
}



