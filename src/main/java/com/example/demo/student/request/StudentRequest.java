package com.example.demo.student.request;

import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * This class represent the api request for student.
 */

@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Data
@AllArgsConstructor
public class StudentRequest {

  private Long id;

  /*
  This field represents the name of the student.
   */
  @NonNull
  @NotBlank(message = "Name field cannot be blank")
  private String name;

  /*
  This field is for the date of birth of the student.
   */

  @NonNull
  private LocalDate dob;

  /*
  This field is for the email of the student.
   */

  @NonNull
  @NotBlank(message = "Email field cannot be blank")
  private String email;


}


