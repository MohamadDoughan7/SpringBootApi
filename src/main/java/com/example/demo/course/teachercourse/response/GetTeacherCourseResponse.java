package com.example.demo.course.teachercourse.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class represents the get teacher course relation response.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetTeacherCourseResponse {

  /*
   * This is the id of the teacher that is assigned to teach the courses.
   * It is a partial primary key.
   */
  private Long teacherId;

  /*
   * This is the id of the course that teachers are assigned to teach.
   * It is a partial primary key.
   */
  private Long courseId;
}