package com.example.demo.course.enrollment.exceptions;

/**
 * This class represents the enrollment not found exception.
 */
public class EnrollmentNotFoundException extends RuntimeException {

  /*
  Exception's constructor.
   */
  public EnrollmentNotFoundException(String message) {
    super(message);
  }
}