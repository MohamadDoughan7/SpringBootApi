package com.example.demo.student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

/**
 * This class represents the student entity.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
public class Student {

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
  private String email;

  /*
   * Transient is used for telling the server that age is not a value that should be inputted,
   * it should be generated based on the date of birth, and it is not stored in the database.
   * Age is calculated and mapped to the age property using the getAge() method.
   */
  private transient Integer age;


}
