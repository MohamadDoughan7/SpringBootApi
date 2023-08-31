package com.example.demo.course.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class represent the api response for displaying the available courses.
 */
@NoArgsConstructor
@Data
@AllArgsConstructor
public class GetCourseResponse {

  /*
  This filed represents the id of the course.
   */
  private Long id;
  /*
  This field represents the name of the course.
   */
  private String name;
  /*
  This field represents the code of the course.
   */
  private String code;
  /*
  This field is for the field that the course covers.
   */
  private String field;
  /*
  This field is for the maximum capacity that the course can fit.
   */
  private Integer capacity;
}