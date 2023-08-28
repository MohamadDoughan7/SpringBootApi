package com.example.demo.distribution.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * This class represents the distribution request.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class AddDistributionRequest {

  /*
   * This is the id of the teacher that teaches the courses.
   * It is a partial primary key.
   */
  @NonNull
  @NotNull(message = "teacher_id field cannot be null")
  private Long teacherId;

  /*
   * This is the id of the course that the teachers are assigned to teach.
   * It is a partial primary key.
   */
  @NonNull
  @NotNull(message = "course_id field cannot be null")
  private Long courseId;
}
