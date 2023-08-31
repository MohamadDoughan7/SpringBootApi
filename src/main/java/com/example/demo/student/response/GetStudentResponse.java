package com.example.demo.student.response;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class represent the api response of all the available students.
 */
@AllArgsConstructor
@Data
@NoArgsConstructor(force = true)
public class GetStudentResponse {
  /*
   This id field is automatically generated for the student by the server.
   It is a unique identifier for each student record.
   */

  private Long id;

  /*
    This field represents the name of the student.
   */

  private String name;

  /*
    This field is for the date of birth of the student.
   */

  private LocalDate dob;

  /*
    This field is for the email of the student.
   */

  private String email;

  /*
   * Transient is used for telling the server that age is not a value that should be inputted,
   * it should be generated based on the date of birth.
   * Age is calculated and mapped to the age property using the getAge() method.
   */
  private Integer age;
}