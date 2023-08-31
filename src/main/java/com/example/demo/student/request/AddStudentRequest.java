package com.example.demo.student.request;

import com.example.demo.shared.annotations.dobvalidator.ValidateDOB;
import com.example.demo.shared.annotations.namevalidator.ValidateName;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class represent the api request for adding a student.
 */
@NoArgsConstructor
@Data
@AllArgsConstructor
public class AddStudentRequest {

  /*
  This field represents the name of the student.
   */
  @NotBlank(message = "Name field cannot be blank")
  @ValidateName
  private String name;

  /*
  This field is for the date of birth of the student.
   */

  @ValidateDOB
  private LocalDate dob;

  /*
  This field is for the email of the student.
   */

  @NotBlank(message = "Email field cannot be blank")
  @Email
  private String email;
}