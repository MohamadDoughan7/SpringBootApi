package com.example.demo.course;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * This class represents the course entity.
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class CourseEntity {

  /*
   * This id field is for generating an id for each course by the server.
   * It is a unique identifier for each course record.
   */
  private Long Id;
  /*
   This field represents the name of the course.
   */
  private String name;
  /*
  This filed represents the code of the course.
   */
  private String code;
  /*
   This field is for the field that the course covers.
   */
  private String field;
  /*
  This field represents the capacity of students the course can fit.
   */
  private Integer capacity;
}