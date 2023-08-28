package com.example.demo.distribution;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * This class represents the distribution relation.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class Distribution {

  /*
   * This is the id of the teacher that teaches the courses.
   * It is a partial primary key.
   */
  @NonNull
  @NotBlank(message = "teacherId field cannot be null")
  private Long teacherId;

  /*
   * This is the id of the course that the teachers are assigned to teach.
   * It is a partial primary key.
   */
  @NonNull
  @NotBlank(message = "courseId field cannot be null")
  private Long courseId;
}
