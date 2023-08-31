package com.example.demo.teacher;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * This class represents the student entity.
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class TeacherEntity {
  /*
   This id field is for generating an id for each student by the server.
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
   This filed represents the subject that the teacher teaches.
   */
  private String subject;
}