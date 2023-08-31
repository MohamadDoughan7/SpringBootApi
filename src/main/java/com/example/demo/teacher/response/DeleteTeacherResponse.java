package com.example.demo.teacher.response;

import lombok.Getter;
import lombok.Setter;

/*
This class is the response to deleting a teacher.
 */
@Getter
@Setter
public class DeleteTeacherResponse {

  /*
  This field is the id of the teacher that is being deleted.
   */
  private long id;

  /*
  This field is the message that will be displayed if the deleting completed successfully.
   */
  private String message = ("Teacher has been deleted successfully");
}
