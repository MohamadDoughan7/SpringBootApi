package com.example.demo.course.teachercourse;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * This interface is a mapper to handle ORM for the teacher course relation.
 */
@Repository
@Mapper
public interface TeacherCourseRepository {

  /**
   * Defining the getAllTeacherCourseRelations method.
   *
   * @return list of teacher course relations.
   */
  List<TeacherCourseEntity> getTeacherCourseRelationsByCourseId(long courseId);

  /**
   * This method is for finding a teacher course relation based on the teacherId and the courseId.
   *
   * @param teacherId of the needed teacher.
   * @param courseId  of the course the teacher is assigned to teach.
   * @return teacher course relation with the entered teacherId and courseId.
   */
  TeacherCourseEntity findByIds(Long teacherId, Long courseId);


  /**
   * Defining the insertTeacherCourseRelation method.
   *
   * @param teacherCourseEntity to be added to the database.
   */
  void insertTeacherCourseRelation(TeacherCourseEntity teacherCourseEntity);


  /**
   * Defining the deleteTeacherCourse method.
   *
   * @param teacherId in the teacher course relation to be deleted.
   * @param courseId  in the teacher course relation to be deleted.
   */
  void deleteTeacherCourseRelationByIds(Long teacherId, Long courseId);

  /**
   * This method is for checking if the teacher course relation with the entered ids is available in
   * the database.
   *
   * @param teacherId of the needed teacher course relations to be found.
   * @param courseId  of the needed teacher course relations to be found.
   * @return true for existing teacher course relations and false for a non-existing one.
   */
  boolean existsByIds(Long teacherId, Long courseId);

  /**
   * Get the count of teacher course relations for a specific teacher.
   *
   * @param teacherId the ID of the course
   * @return the count of teacher course relations
   */
  int getTeacherCourseRelationByTeacherId(Long teacherId);

  /**
   * Get the count of teacher course relations for a specific teacher.
   *
   * @param courseId the ID of the course
   * @return the count of enrollments
   */
  int getTeacherCourseCountByCourseId(Long courseId);
}