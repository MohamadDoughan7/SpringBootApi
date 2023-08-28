package com.example.demo.enrollment;

import com.example.demo.enrollment.request.AddEnrollmentRequest;
import com.example.demo.enrollment.response.EnrollmentResponse;
import com.example.demo.enrollment.response.EnrollmentResponseList;
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
  @GetMapping
  public ResponseEntity<EnrollmentResponseList> getEnrollments() {

    /*
    Get the list of enrollments from the service.
     */
    EnrollmentResponseList enrollments = enrollmentService.getEnrollments();

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
  public ResponseEntity<EnrollmentResponse> createNewEnrollment(@Valid
  @RequestBody AddEnrollmentRequest request) {

    /*
    Preparing the response.
     */
    EnrollmentResponse newEnrollment = enrollmentService.addNewEnrollment(
        request.getStudentId(),
        request.getCourseId());

    /*
    Generating the response.
     */
    return ResponseEntity.ok(newEnrollment);
  }

  /**
   * Deletes an enrollment from the database.
   *
   * @param studentId the ID of the student of the enrollment to be deleted
   * @param courseId  the ID of the course of the enrollment to be deleted
   * @return the deletion result
   */
  @DeleteMapping(path = "{studentId}-{courseId}")
  public ResponseEntity<String> deleteEnrollment(
      @PathVariable("studentId") Long studentId,
      @PathVariable("courseId") Long courseId) {

    /*
    Preparing the response.
     */
    enrollmentService.deleteEnrollmentByIds(studentId, courseId);

    /*
    Generating the response.
     */
    return ResponseEntity.ok("Enrollment deleted successfully");
  }
}







