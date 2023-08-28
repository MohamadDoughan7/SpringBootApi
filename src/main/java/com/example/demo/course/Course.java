package com.example.demo.course;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * This class represents the course entity.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
public class Course {

  /*
   * This id field is for generating an id for each course by the server.
   * It is a unique identifier for each course record.
   */
  private Long courseId;

  /*
   * This field represents the name of the course.
   * It cannot be null.
   */
  @NonNull
  @NotBlank(message = "Name field cannot be null")
  private String courseName;

  /*
   * This field is for the field that the course covers.
   * It cannot be null.
   */
  @NonNull
  @NotBlank (message = "field cannot be blank")
  private String field;

  @NonNull
  @NotNull(message = "Capacity field cannot be null")
  private Integer capacity;

}
