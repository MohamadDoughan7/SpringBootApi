package com.example.demo.course;

import com.example.demo.course.request.AddCourseRequest;
import com.example.demo.course.response.CourseResponse;
import com.example.demo.course.response.CourseResponseList;
import com.example.demo.shared.Validation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class is for handling the course-related HTTP requests.
 */
@RestController
@RequestMapping(path = "api/v1/course")
@AllArgsConstructor
public class CourseController {

  private final CourseService courseService;

  /**
   * Retrieves the list of available courses.
   *
   * @return the list of courses
   */
  @GetMapping
  public ResponseEntity<CourseResponseList> getCourses() {
    /*
    Get the list of courses from the service.
     */
    CourseResponseList courses = courseService.getCourses();
    /*
    Generating the response.
     */
    return new ResponseEntity<>(courses, HttpStatus.OK);
  }

  /**
   * Adds a new course to the repository.
   *
   * @param request the course request body to be added
   * @return the added course response
   */
  @PostMapping
  public ResponseEntity<CourseResponse> createNewCourse(@Valid
  @RequestBody AddCourseRequest request) {
    /*
    Validate the course name before adding a new course.
     */
    //TODO: this validation need to be done on annotation level.
    Validation.validateName(request.getCourseName());
    /*
    Preparing the response.
     */
    CourseResponse newCourse = courseService.addCourse(
        request.getCourseName(),
        request.getField(),
        request.getCapacity());
    /*
    Generating the response.
     */
    return ResponseEntity.ok(newCourse);
  }

  /**
   * Deletes a course from the database.
   *
   * @param courseId the ID of the course to be deleted
   * @return the deletion result
   */
  @DeleteMapping(path = "{courseId}")
  public ResponseEntity<String> deleteCourse(@PathVariable("courseId") Long courseId) {
    /*
    Preparing the response.
     */
    courseService.deleteCourseById(courseId);
    /*
    Generating the response.
     */
    return ResponseEntity.ok("Course deleted successfully");
  }

  /**
   * Updates the attributes of a course.
   *
   * @param courseId the ID of the course to be updated
   * @param courseName  the updated name
   * @param field  the updated filed
   * @param capacity the updated capacity
   * @return the update result
   */
  @PutMapping("{courseId}")
  public ResponseEntity<String> updateCourse(
      @PathVariable("courseId") Long courseId,
      @RequestParam(required = false) String courseName,
      @RequestParam(required = false) String field,
      @RequestParam(required = false) Integer capacity) {
    /*
    Validating the course name before updating a course.
     */
    //TODO can be implemented on annotation level.
    Validation.validateName(courseName);
    /*
    Preparing the response.
     */
    courseService.updateCourseById(courseId, courseName, field, capacity);
    /*
    Generating the response.
     */
    return ResponseEntity.ok("Course updated successfully");
  }
}





