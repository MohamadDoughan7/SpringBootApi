package com.example.demo.enrollmentmetric.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * This class represents the enrollment metric response.
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class EnrollmentMetricResponse {

  /*
   * This is the id of the student that is enrolled in the courses.
   * It is a partial primary key.
   */
  private Long studentId;
  /*
  This is the name of the student with the specified id.
   */
  private String studentName;

  /*
   * This is the id of the course that students are enrolled in.
   * It is a partial primary key.
   */
  private Long courseId;
  /*
  This is the name of the course with the specified id.
   */
  private String courseName;
  /*
  This is the code of the course with the specified id.
   */
  private String code;
  /*
  This is the id of the teacher that teaches the course.
   */
  private Long teacherId;
  /*
  This is the name of the teacher that teaches the course.
   */
  private String teacherName;
}