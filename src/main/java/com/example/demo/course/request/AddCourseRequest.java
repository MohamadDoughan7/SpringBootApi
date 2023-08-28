package com.example.demo.course.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * This class represent the api request for the course.
 */

@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Data
@AllArgsConstructor
public class AddCourseRequest {

  /*
  This filed represents the id of the course.
   */
  private Long courseId;

  /*
  This field represents the name of the course.
   */
  @NonNull
  @NotBlank(message = "Name field cannot be blank")
  private String courseName;

  /*
  This field is for the field that the course covers.
   */
  @NonNull
  @NotBlank(message = "Field cannot be blank")
  private String field;

  /*
  This field is for the maximum capacity that the course can fit.
   */
  @NonNull
  @NotNull(message = "Capacity field cannot be null")
  private Integer capacity;


}


