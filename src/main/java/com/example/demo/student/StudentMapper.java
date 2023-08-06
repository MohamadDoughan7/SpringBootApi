package com.example.demo.student;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * This interface is a mapper to handle ORM for the student entity.
 */
@Repository
@Mapper
public interface StudentMapper {

  /**
   * Defining the getAllStudents method.
   *
   * @return list of students.
   */
  List<Student> getAllStudents();

  /**
   * This method is for finding a student based ion his id.
   *
   * @param id of the needed student.
   * @return student with the entered id.
   */
  Student findById(Long id);

  /**
   * This method is for finding a student based on his email.
   *
   * @param email of the needed student.
   * @return student with the entered email.
   */
  Student findStudentByEmail(String email);

  /**
   * Defining the insertStudent method.
   *
   * @param student to be added to the database.
   */
  void insertStudent(Student student);

  /**
   * Defining the updateStudent method.
   *
   * @param student to be updated.
   */
  void updateStudent(Student student);

  /**
   * Defining the deleteStudent method.
   *
   * @param id of the student tobe deleted.
   */
  void deleteStudentById(Long id);

  /**
   * This method is for checking if the student with the entered id is available in the database.
   *
   * @param id of the needed student to be found.
   * @return true for existing student and false for a non-existing student.
   */
  boolean existsById(Long id);
}
