package com.example.demo.student.exceptions;

/**
 * This class represents the already existing email exception..
 */
public class EmailAlreadyTakenException extends IllegalStateException{
  /*
  Exception's constructor.
   */
  public EmailAlreadyTakenException(String message) {
    super(message);
  }
}