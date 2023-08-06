package com.example.demo.student.exceptions;

/**
 * This class represents the invalid email format exception.
 */
public class InvalidEmailFormatException extends IllegalStateException {

  /*
  Exception's constructor.
   */
  public InvalidEmailFormatException(String message) {
    super(message);
  }
}
