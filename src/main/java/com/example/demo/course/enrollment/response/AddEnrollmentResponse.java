package com.example.demo.course.enrollment.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class represents the enrollment response.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddEnrollmentResponse {

  /*
   * This is the id of the student that is enrolled in the courses.
   * It is a partial primary key.
   */
  private Long studentId;

  /*
   * This is the id of the course that students are enrolled in.
   * It is a partial primary key.
   */
  private Long courseId;
}