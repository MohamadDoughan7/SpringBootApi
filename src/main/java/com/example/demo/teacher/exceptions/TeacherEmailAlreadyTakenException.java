package com.example.demo.teacher.exceptions;

/**
 * This class represents the already existing teacher email exception..
 */
public class TeacherEmailAlreadyTakenException extends IllegalStateException {

  /*
  Exception's constructor.
   */
  public TeacherEmailAlreadyTakenException(String message) {
    super(message);
  }

}
