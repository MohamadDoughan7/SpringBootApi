package com.example.demo.teacher.exceptions;

/**
 * This class represents the teacher not found exception.
 */
public class TeacherNotFoundException extends RuntimeException {

  /*
  Exception's constructor.
   */
  public TeacherNotFoundException(String message) {
    super(message);
  }
}