package com.example.demo.student.response;

import lombok.Getter;
import lombok.Setter;

/**
 * This class is the response to updating a student.
 */
@Getter
@Setter
public class UpdateStudentResponse {

  /*
  This field is the id of the student that is being updated.
   */
  private long id;

  /*
  This field is the message that will be displayed if the updated completed successfully
   */
  private String message = ("Student has been updated successfully");
}
