package com.example.demo.enrollment.exceptions;

/**
 * This class represents the enrollment already exists exception.
 */
public class EnrollmentAlreadyExistsException extends RuntimeException {

  /*
  Exception's constructor.
   */
  public  EnrollmentAlreadyExistsException(String message) {
    super(message);
  }
}



