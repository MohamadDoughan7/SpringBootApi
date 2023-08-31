package com.example.demo.course;

import com.example.demo.course.exceptions.CourseNameAlreadyTakenException;
import com.example.demo.course.exceptions.CourseNotFoundException;
import com.example.demo.course.response.AddCourseResponse;
import com.example.demo.course.response.GetCourseResponse;
import com.example.demo.teacher.exceptions.TeacherEmailAlreadyTakenException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

/**
 * This class is for performing the business logic for the methods within the course package.
 */
@Service
@RequiredArgsConstructor
@Log4j2
public class CourseService {

  private final ModelMapper modelMapper = new ModelMapper();
  private final CourseRepository courseRepository;

  /**
   * Retrieves the list of available courses.
   *
   * @return list of available courses.
   */
  public List<GetCourseResponse> getCourses() {
    /*
    Log info about displaying the list of courses.
     */
    log.info("List of existing courses has been displayed.");
    /*
    Get the list of courses from the service using the CourseRepository interface,
    and then build the response.
     */
    return courseRepository
        .getAllCourses()
        .parallelStream()
        .map(courseEntity -> modelMapper.map(courseEntity, GetCourseResponse.class))
        .collect(Collectors.toList());
  }

  /**
   * Adds a new course and saves it in the database.
   *
   * @param name     the name of the course
   * @param code     the code of the course
   * @param field    the filed that the course covers.
   * @param capacity the maximum capacity that teh course can fit.
   * @return the added course
   * @throws TeacherEmailAlreadyTakenException if the email is already taken
   */
  public AddCourseResponse addCourse(
      String name,
      String code,
      String field,
      Integer capacity) {
    /*
     Check if the courseEntity already exists in the database.
     */
    if (courseRepository.findCourseByName(name) != null) {
      String errorMessage = "Course already exists: " + name;
      throw new CourseNameAlreadyTakenException(errorMessage);
    }
    /*
    Create a new CourseEntity object
    */
    CourseEntity courseEntity = new CourseEntity();
    courseEntity.setName(name);
    courseEntity.setCode(code);
    courseEntity.setField(field);
    courseEntity.setCapacity(capacity);
    /*
    Insert a new courseEntity in database.
     */
    courseRepository.insertCourse(courseEntity);
    /*
     Log info about the new courseEntity added.
     */
    log.info("New course added: {}", courseEntity);
    /*
     Map the newly inserted courseEntity to a AddCourseResponse object and return it.
     */
    return modelMapper.map(courseEntity, AddCourseResponse.class);
  }

  /**
   * Deletes a course from the database.
   *
   * @param id the ID of the course to be deleted
   * @throws CourseNotFoundException if the course with the given ID does not exist
   */
  public void deleteCourseById(Long id) {
    /*
     Check if the course exists in the database based on the provided id.
     */
    if (!courseRepository.existsById(id)) {
      String errorMessage = "Course with id " + id + " does not exist.";
      throw new CourseNotFoundException(errorMessage);
    }
    /*
     Delete the course from the database using the CourseRepository interface.
     */
    courseRepository.deleteCourseById(id);
    log.info("Course with id " + id + " has been deleted.");
  }

  /**
   * Updates the attributes of a course in the database.
   *
   * @param id       the ID of the course to be updated
   * @param name     the updated name
   * @param field    the updated filed
   * @param capacity the updated capacity
   * @throws CourseNotFoundException         if the course with the given ID does not exist
   * @throws CourseNameAlreadyTakenException if the course name is already taken
   */
  public void updateCourseById(
      Long id,
      String name,
      String code,
      String field,
      Integer capacity) {
    /*
     Find the existing courseEntity by ID using the CourseRepository interface.
     */
    CourseEntity courseEntity = courseRepository.findById(id);
    if (courseEntity == null) {
      String errorMessage = "Course with id " + id + " does not exist.";
      throw new CourseNotFoundException(errorMessage);
    }
    /*
     Update the name, code, field and capacity of the courseEntity.
     */
    courseEntity.setName(name);
    courseEntity.setCode(code);
    courseEntity.setField(field);
    courseEntity.setCapacity(capacity);
    /*
     Update the courseEntity in the database using the CourseRepository interface.
     */
    courseRepository.updateCourse(courseEntity);
    log.info("Course with id " + id + " has been updated.");
  }
}