package com.example.demo.teacher;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * This interface is a mapper to handle ORM for the student entity.
 */
@Repository
@Mapper
public interface TeacherRepository {

  /**
   * Defining the getAllTeachers method.
   *
   * @return list of teachers.
   */
  List<TeacherEntity> getAllTeachers();

  /**
   * This method is for finding a teacher based ion his id.
   *
   * @param id of the needed teacher.
   * @return teacher with the entered id.
   */
  TeacherEntity findById(Long id);

  /**
   * This method is for finding a teacher based on his email.
   *
   * @param email of the needed teacher.
   * @return teacher with the entered email.
   */
  TeacherEntity findTeacherByEmail(String email);

  /**
   * Defining the insertTeacher method.
   *
   * @param teacherEntity to be added to the database.
   */
  void insertTeacher(TeacherEntity teacherEntity);

  /**
   * Defining the updateTeacher method.
   *
   * @param teacherEntity to be updated.
   */
  void updateTeacher(TeacherEntity teacherEntity);

  /**
   * Defining the deleteTeacher method.
   *
   * @param id of the teacher to be deleted.
   */
  void deleteTeacherById(Long id);

  /**
   * This method is for checking if the teacher with the entered id is available in the database.
   *
   * @param id of the needed teacher to be found.
   * @return true for existing teacher and false for a non-existing teacher.
   */
  boolean existsById(Long id);
}