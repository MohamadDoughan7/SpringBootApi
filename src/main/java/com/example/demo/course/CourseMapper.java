package com.example.demo.course;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * This interface is a mapper to handle ORM for the student entity.
 */
@Repository
@Mapper
public interface CourseMapper {

  /**
   * Defining the getAllCourses method.
   *
   * @return list of courses.
   */
  List<Course> getAllCourses();

  /**
   * This method is for finding a course based on his id.
   *
   * @param courseId of the needed course.
   * @return course with the entered id.
   */
  Course findById(Long courseId);

  /**
   * This method is for finding a course based on his name.
   *
   * @param courseName of the needed course.
   * @return course with the entered email.
   */
  Course findCourseByName(String courseName);

  /**
   * Defining the insertCourse method.
   *
   * @param course to be added to the database.
   */
  void insertCourse(Course course);

  /**
   * Defining the updateCourse method.
   *
   * @param course to be updated.
   */
  void updateCourse(Course course);

  /**
   * Defining the deleteCourse method.
   *
   * @param courseId of the course to be deleted.
   */
  void deleteCourseById(Long courseId);

  /**
   * This method is for checking if the course with the entered id is available in the database.
   *
   * @param courseId of the needed course to be found.
   * @return true for existing course and false for a non-existing course.
   */
  boolean existsById(Long courseId);
}
