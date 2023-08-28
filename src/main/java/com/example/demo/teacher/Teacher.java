package com.example.demo.teacher;

import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * This class represents the student entity.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
public class Teacher {

  /*
   * This id field is for generating an id for each student by the server.
   * It is a unique identifier for each student record.
   */

  private Long id;

  /*
   * This field represents the name of the student.
   * It cannot be null.
   */
  @NonNull
  @NotBlank(message = "Name field cannot be null")
  private String name;

  /*
   * This field is for the date of birth of the student.
   * It cannot be null.
   */
  @NonNull
  private LocalDate dob;

  /*
   * This field is for the email of the student.
   * It cannot be null.
   */
  @NonNull
  @NotBlank(message = "Email field cannot be blank")
  private String email;

  @NonNull
  private String subject;

}