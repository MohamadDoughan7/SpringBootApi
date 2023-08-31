package com.example.demo.course.teachercourse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * This class represents the teacher course relation.
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class TeacherCourseEntity {

  /*
   * This is the id of the teacher that teaches the courses.
   * It is a partial primary key.
   */
  private Long teacherId;

  /*
   * This is the id of the course that the teachers are assigned to teach.
   * It is a partial primary key.
   */
  private Long courseId;
}