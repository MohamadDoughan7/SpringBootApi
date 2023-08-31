package com.example.demo.student.exceptions;

/**
 * This class represents the student not found exception.
 */
public class StudentNotFoundException extends RuntimeException {

  /*
  Exception's constructor.
   */
  public StudentNotFoundException(String message) {
    super(message);
  }
}