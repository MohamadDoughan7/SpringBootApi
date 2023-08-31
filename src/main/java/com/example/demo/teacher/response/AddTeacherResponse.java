package com.example.demo.teacher.response;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class represent the api response of adding a new teacher.
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
public class AddTeacherResponse {

  /*
   This id field is automatically generated for the student by the server.
   It is a unique identifier for each teacher record.
   */

  private Long id;

  /*
    This field represents the name of the teacher.
   */

  private String name;

  /*
    This field is for the date of birth of the teacher.
   */

  private LocalDate dob;

  /*
    This field is for the email of the teacher.
   */

  private String email;

  /*
  This filed represents the subject that the teacher teaches.
   */
  private String subject;
}