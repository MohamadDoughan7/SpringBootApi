package com.example.demo.course.enrollment.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteEnrollmentResponse {
  /*
  This field represents the id of the student enrolled in the course.
   */
  private long studentId;
  /*
  This field represents the id of the course that the student is enrolled in.
   */
  private long courseId;
  /*
  This field is the message that will be displayed if the deletion completed successfully.
   */
  private String message =("Enrollment has been deleted successfully");
}
