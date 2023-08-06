package com.example.demo.student.exceptions;

/**
 * This class represents the invalid name exception.
 */
public class InvalidNameException extends IllegalStateException {

  /*
Exception's constructor.
 */
  public InvalidNameException(String message) {
    super(message);
  }
}


