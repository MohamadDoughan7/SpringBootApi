package com.example.demo.teacher.request;

import com.example.demo.shared.annotations.dobvalidator.ValidateDOB;
import com.example.demo.shared.annotations.namevalidator.ValidateName;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class represent the api post request for teachers.
 */

@NoArgsConstructor
@Data
@AllArgsConstructor
public class AddTeacherRequest {

  /*
  This field represents the name of the teacher.
   */
  @NotBlank(message = "Name field cannot be blank")
  @ValidateName
  private String name;

  /*
  This field is for the date of birth of the teacher.
   */
  @ValidateDOB
  private LocalDate dob;

  /*
  This field is for the email of the teacher.
   */
  @NotBlank(message = "Email field cannot be blank")
  @Email(message = "Invalid Email format")
  private String email;

  /*
  This field represents the subject that the teacher teaches.
   */
  @NotBlank(message = "Subject filed cannot be blank")
  @ValidateName (message = "Invalid Subject Format")
  private String subject;
}