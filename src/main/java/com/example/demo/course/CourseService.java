package com.example.demo.course;

import com.example.demo.course.exceptions.CourseNameAlreadyTakenException;
import com.example.demo.course.exceptions.CourseNotFoundException;
import com.example.demo.course.response.CourseResponse;
import com.example.demo.course.response.CourseResponseList;
import com.example.demo.teacher.exceptions.TeacherEmailAlreadyTakenException;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

/**
 * This class is for performing the business logic for the methods within the course package.
 */
@Service
@RequiredArgsConstructor
@Log4j2
public class CourseService {


  private final CourseMapper courseMapper;

  /**
   * Retrieves the list of available courses.
   *
   * @return list of available courses.
   */

  public CourseResponseList getCourses() {
    /*
    Log info about displaying the list of courses.
     */
    log.info("List of existing courses has been displayed.");

    /*
    Get the list of courses from the service using the CourseMapper interface,
    and then build the response.
     */
    return new CourseResponseList(
        courseMapper.getAllCourses().stream()
            .map(course -> new CourseResponse(
                course.getCourseId(),
                course.getCourseName(),
                course.getField(),
                course.getCapacity()))
            .collect(Collectors.toList())
    );
  }


  /**
   * Adds a new course and saves it in the database.
   *
   * @param courseName  the name of the course
   * @param field   the filed that the course covers.
   * @param capacity the maximum capacity that teh course can fit.
   * @return the added course
   * @throws TeacherEmailAlreadyTakenException if the email is already taken
   */

  public CourseResponse addCourse(
      String courseName,
      String field,
      Integer capacity) {
    /*
     Check if the course already exists in the database.
     */
    if (courseMapper.findCourseByName(courseName) != null) {
      String errorMessage = "Course already exists: " + courseName;
      throw new CourseNameAlreadyTakenException(errorMessage);
    }

    /*
     Create a new Course object and insert it into the database using the CourseMapper interface.
     */
    Course course = new Course(courseName, field, capacity);
    courseMapper.insertCourse(course);

    /*
     Log info about the new course added.
     */
    log.info("New course added: {}", course);

    /*
     Map the newly inserted course to a CourseResponse object and return it.
     */
    return new CourseResponse(course.getCourseId(), course.getCourseName(), course.getField(),
        course.getCapacity());
  }
  /**
   * Deletes a course from the database.
   *
   * @param courseId the ID of the course to be deleted
   * @throws CourseNotFoundException if the course with the given ID does not exist
   */
  public void deleteCourseById(Long courseId) {
    /*
     Check if the course exists in the database based on the provided id.
     */
    if (!courseMapper.existsById(courseId)) {
      String errorMessage = "Course with id " + courseId + " does not exist.";
      throw new CourseNotFoundException(errorMessage);
    }
    /*
     Delete the course from the database using the CourseMapper interface.
     */
    courseMapper.deleteCourseById(courseId);
    log.info("Course with id " + courseId + " has been deleted.");
  }

  /**
   * Updates the attributes of a course in the database.
   *
   * @param courseId the ID of the course to be updated
   * @param courseName  the updated name
   * @param field  the updated filed
   * @param capacity the updated capacity
   * @throws CourseNotFoundException   if the course with the given ID does not exist
   * @throws CourseNameAlreadyTakenException if the course name is already taken
   */
  public void updateCourseById(
      Long courseId,
      String courseName,
      String field,
      Integer capacity){
    /*
     Find the existing course by ID using the CourseMapper interface.
     */
    Course course = courseMapper.findById(courseId);
    if (course == null) {
      String errorMessage = "Course with id " + courseId + " does not exist.";
      throw new CourseNotFoundException(errorMessage);
    }
    /*
     Update the name and field and capacity of the course.
     */
      course.setCourseName(courseName);
      course.setField(field);
      course.setCapacity(capacity);

    /*
     Update the course in the database using the CourseMapper interface.
     */
    courseMapper.updateCourse(course);
    log.info("Course with id " + courseId + " has been updated.");
  }
}