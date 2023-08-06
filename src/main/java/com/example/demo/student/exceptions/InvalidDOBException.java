package com.example.demo.student.exceptions;

/**
 * This class represents the invalid date of birth exception.
 */
public class InvalidDOBException extends IllegalStateException {

  /*
  Exception's constructor.
   */
  public InvalidDOBException(String message) {
    super(message);
  }
}
