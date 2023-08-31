package com.example.demo.course;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * This interface is a mapper to handle ORM for the student entity.
 */
@Repository
@Mapper
public interface CourseRepository {

  /**
   * Defining the getAllCourses method.
   *
   * @return list of courses.
   */
  List<CourseEntity> getAllCourses();

  /**
   * This method is for finding a course based on his id.
   *
   * @param id of the needed course.
   * @return course with the entered id.
   */
  CourseEntity findById(Long id);

  /**
   * This method is for finding a course based on his name.
   *
   * @param name of the needed course.
   * @return course with the entered email.
   */
  CourseEntity findCourseByName(String name);

  /**
   * Defining the insertCourse method.
   *
   * @param courseEntity to be added to the database.
   */
  void insertCourse(CourseEntity courseEntity);

  /**
   * Defining the updateCourse method.
   *
   * @param courseEntity to be updated.
   */
  void updateCourse(CourseEntity courseEntity);

  /**
   * Defining the deleteCourse method.
   *
   * @param id of the course to be deleted.
   */
  void deleteCourseById(Long id);

  /**
   * This method is for checking if the course with the entered id is available in the database.
   *
   * @param id of the needed course to be found.
   * @return true for existing course and false for a non-existing course.
   */
  boolean existsById(Long id);
}