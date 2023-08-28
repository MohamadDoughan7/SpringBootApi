package com.example.demo.teacher.request;

import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * This class represent the api post request for teachers.
 */

@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Data
@AllArgsConstructor
public class TeacherRequest {

  private Long id;

  /*
  This field represents the name of the teacher.
   */
  @NonNull
  @NotBlank(message = "Name field cannot be blank")
  private String name;

  /*
  This field is for the date of birth of the teacher.
   */

  @NonNull
  private LocalDate dob;

  /*
  This field is for the email of the teacher.
   */

  @NonNull
  @NotBlank(message = "Email field cannot be blank")
  private String email;

  /*
  This field represents the subject that the teacher teaches.
   */
  @NonNull
  private String subject;

}


