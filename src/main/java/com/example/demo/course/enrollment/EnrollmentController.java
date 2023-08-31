package com.example.demo.course.enrollment;

import com.example.demo.course.enrollment.request.AddEnrollmentRequest;
import com.example.demo.course.enrollment.request.DeleteEnrollmentRequest;
import com.example.demo.course.enrollment.response.AddEnrollmentResponse;
import com.example.demo.course.enrollment.response.DeleteEnrollmentResponse;
import com.example.demo.course.enrollment.response.EnrollmentResponses;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class is for handling the enrollment-related HTTP requests.
 */
@RestController
@RequestMapping(path = "api/v1/enrollment")
@AllArgsConstructor
public class EnrollmentController {

  private final EnrollmentService enrollmentService;

  /**
   * Retrieves the list of available enrollment.
   *
   * @return the list of enrollments
   */
  @GetMapping(path = "course/{courseId}")
  public ResponseEntity<EnrollmentResponses> getEnrollmentsByCourseId(
      @PathVariable("courseId") Long courseId)
  {
    /*
    Get the list of enrollments from the service.
     */
    EnrollmentResponses enrollments = enrollmentService.getEnrollmentsByCourseId(courseId);

    /*
    Generating the response.
     */
    return new ResponseEntity<>(enrollments, HttpStatus.OK);
  }

  /**
   * Adds a new enrollment to the database.
   *
   * @param request the enrollment request body to be added
   * @return the added enrollment response
   */
  @PostMapping
  public ResponseEntity<AddEnrollmentResponse> addNewEnrollment(@Valid
  @RequestBody AddEnrollmentRequest request) {
    /*
    Preparing the response.
     */
    AddEnrollmentResponse newEnrollment = enrollmentService.addNewEnrollment(
        request.getStudentId(),
        request.getCourseId());
    /*
    Generating the response.
     */
    return ResponseEntity.ok(newEnrollment);
  }

  /**
   * Deletes an enrollment from the database.
   * @param request to delete a student
   * @return response of the delete student
   */
  @DeleteMapping
  public ResponseEntity<DeleteEnrollmentResponse> deleteEnrollment(
      @RequestBody DeleteEnrollmentRequest request){
    /*
    Preparing the response.
     */
    enrollmentService.deleteEnrollmentByIds(request.getStudentId(), request.getCourseId());
    DeleteEnrollmentResponse response = new DeleteEnrollmentResponse();
    response.setStudentId(request.getStudentId());
    response.setCourseId(request.getCourseId());
    /*
    Generating the response.
     */
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}