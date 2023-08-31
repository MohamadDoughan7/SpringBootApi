package com.example.demo.course.response;

import lombok.Getter;
import lombok.Setter;

/**
 * This class is the response to updating a course.
 */
@Getter
@Setter
public class UpdateCourseResponse {

  /*
  This field is the id of the course that will be updated.
   */
  private long id;
  /*
  This field is the message that will be displayed if the update completed successfully.
   */
  private String message = ("Course has been updated successfully");
}
