package com.example.demo.student.response;

import lombok.Getter;
import lombok.Setter;

/**
 * This class is the response to deleting a student.
 */
@Getter
@Setter
public class DeleteStudentResponse {

  /*
  This filed is the id of the student that is being deleted.
   */
  private long id;

  /*
  This field is the message that will be displayed if the deletion completed successfully.
   */
  private String message = ("Student has been deleted successfully");
}
