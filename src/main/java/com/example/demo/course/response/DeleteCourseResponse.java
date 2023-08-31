package com.example.demo.course.response;

import lombok.Getter;
import lombok.Setter;

/**
 * This class represents the response to deleting a course.
 */
@Getter
@Setter
public class DeleteCourseResponse {

  /*
  This field is the id of the course to be deleted
   */
  private long id;
  /*
  This field is the message that will be displayed if the deletion completed successfully.
   */
  private String message = ("Course has been deleted successfully");
}
