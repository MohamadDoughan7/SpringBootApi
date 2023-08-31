package com.example.demo.teacher.response;

import lombok.Getter;
import lombok.Setter;

/**
 * This class is the response to updating a teacher.
 */
@Getter
@Setter
public class UpdateTeacherResponse {

  /*
  This field is the id of the teacher that is being updated.
   */
  private long id;

  /*
  This field is the message that will be displayed if the update completed successfully.
   */
  private String message = ("Teacher has been updated successfully");
}