package com.example.demo.student.exceptions;

/**
 * This class represents the already existing email exception..
 */
public class StudentEmailAlreadyTakenException extends IllegalStateException {

  /*
  Exception's constructor.
   */
  public StudentEmailAlreadyTakenException(String message) {
    super(message);
  }
}