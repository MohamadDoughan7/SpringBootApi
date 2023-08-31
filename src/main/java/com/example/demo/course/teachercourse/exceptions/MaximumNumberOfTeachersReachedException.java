package com.example.demo.course.teachercourse.exceptions;

/**
 * This class represents the maximum number of courses reached for a teacher exception.
 */
public class MaximumNumberOfTeachersReachedException extends RuntimeException {

  /*
  Exception's constructor.
   */
  public MaximumNumberOfTeachersReachedException(String message) {
    super(message);
  }
}



