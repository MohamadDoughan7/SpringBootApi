package com.example.demo.course.request;

import com.example.demo.course.annotations.coursecodevalidator.ValidateCourseCode;
import com.example.demo.course.annotations.coursenamevalidator.ValidateCourseName;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class represent the api  update request for the course.
 */

@NoArgsConstructor(force = true)
@Data
@AllArgsConstructor
public class UpdateCourseRequest {

  /*
  This field represents the name of the course.
   */
  @NotBlank(message = "Name field cannot be blank")
  @ValidateCourseName
  private String name;
  /*
  This field represents the name of the course.
  */
  @NotBlank(message = "Code field cannot be blank")
  @ValidateCourseCode
  private String code;

  /*
  This field is for the field that the course covers.
   */
  @NotBlank(message = "Field cannot be blank")
  private String field;

  /*
  This field is for the maximum capacity that the course can fit.
   */
  @NotNull(message = "Capacity field cannot be null")
  private Integer capacity;
}