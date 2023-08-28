package com.example.demo.enrollment;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * This class represents the enrollment relation.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class Enrollment {

  /*
   * This is the id of the student that is enrolled in the courses.
   * It is a partial primary key.
   */
  @NonNull
  @NotBlank(message = "student_id field cannot be null")
  private Long studentId;

  /*
   * This is the id of the course that students are enrolled in.
   * It is a partial primary key.
   */
  @NonNull
  @NotBlank(message = "course_id field cannot be null")
  private Long courseId;
}