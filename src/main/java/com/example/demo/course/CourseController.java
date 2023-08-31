package com.example.demo.course;

import com.example.demo.course.request.AddCourseRequest;
import com.example.demo.course.request.UpdateCourseRequest;
import com.example.demo.course.response.AddCourseResponse;
import com.example.demo.course.response.CourseResponses;
import com.example.demo.course.response.DeleteCourseResponse;
import com.example.demo.course.response.GetCourseResponse;
import com.example.demo.course.response.UpdateCourseResponse;
import jakarta.validation.Valid;
import java.util.List;
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
  public ResponseEntity<CourseResponses> getCourses() {
    /*
    Get the list of course from the service.
     */
    List<GetCourseResponse> courses = courseService.getCourses();
    CourseResponses courseResponses = new CourseResponses(courses);
    /*
    Generating the response.
     */
    return new ResponseEntity<>(courseResponses, HttpStatus.OK);
  }

  /**
   * Adds a new course to the repository.
   *
   * @param request the course request body to be added
   * @return the added course response
   */
  @PostMapping
  public ResponseEntity<AddCourseResponse> addNewCourse(
      @Valid @RequestBody AddCourseRequest request) {
    /*
    Preparing the response.
     */
    AddCourseResponse newAddCourseResponse = courseService.addCourse(
        request.getName(),
        request.getCode(),
        request.getField(),
        request.getCapacity());
    /*
    Generating the response.
     */
    return ResponseEntity.ok(newAddCourseResponse);
  }

  /**
   * Deletes a course from the database.
   *
   * @param id the ID of the course to be deleted
   * @return the deletion result
   */
  @DeleteMapping(path = "{id}")
  public ResponseEntity<DeleteCourseResponse> deleteCourse(
      @PathVariable("id") Long id) {
    /*
    Preparing the response.
     */
    courseService.deleteCourseById(id);
    DeleteCourseResponse response = new DeleteCourseResponse();
    response.setId(id);
    /*
    Generating the response.
     */
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  /**
   * Updates the attributes of a course.
   * @param id the id of the course to be deleted
   * @param request the request sent to update a course
   * @return the response of teh updated course.
   */
  @PutMapping("{id}")
  public ResponseEntity<UpdateCourseResponse> updateCourse(
      @PathVariable("id") Long id,
      @RequestBody UpdateCourseRequest request){
    /*
    Preparing the response.
     */
    courseService.updateCourseById(
        id,
        request.getName(),
        request.getCode(),
        request.getField(),
        request.getCapacity());
    UpdateCourseResponse response = new UpdateCourseResponse();
    response.setId(id);
    /*
    Generating the response.
     */
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}