package com.example.demo.course.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class represent the api request for the course.
 */


@NoArgsConstructor
@Data
@AllArgsConstructor
public class CourseResponse {

  /*
  This filed represents the id of the course.
   */
  private Long courseId;

  /*
  This field represents the name of the course.
   */
  private String courseName;

  /*
  This field is for the field that the course covers.
   */
  private String field;

  /*
  This field is for the maximum capacity that the course can fit.
   */
  private Integer capacity;


}


