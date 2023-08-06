package com.example.demo.student.response;

import java.time.LocalDate;
import java.time.Period;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class represent the api response of the student.
 */
@AllArgsConstructor
@Data
@NoArgsConstructor(force = true)
public class StudentResponse {


  /*
   This id field is for generating an id for each student by the server.
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

  /**
   * This method is for calculating the age of the student based on his date of birth.
   *
   * @return the age of the student
   */
  public Integer getAge() {
    return Period.between(dob, LocalDate.now()).getYears();
  }
}
