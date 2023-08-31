package com.example.demo.course.teachercourse.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteTeacherCourseResponse {
  /*
  This field represents the id of the teacher that teaches the course.
   */
  private long teacherId;
  /*
  This field represents the id of the course that the teacher teaches..
   */
  private long courseId;
  /*
  This field is the message that will be displayed if the deletion completed successfully.
   */
  private String message =("Enrollment has been deleted successfully");
}
