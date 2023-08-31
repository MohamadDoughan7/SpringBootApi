package com.example.demo.enrollmentmetric;

import com.example.demo.enrollmentmetric.response.EnrollmentMetricResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class is for handling all the http requests within the enrollmentMetric package.
 */
@RestController
@RequestMapping(path = "api/v1/enrollmentMetric")
@AllArgsConstructor
public class EnrollmentMetricController {

  private final EnrollmentMetricService enrollmentMetricService;

  /**
   * This method is for displaying the enrollment metric for the specified studentId and courseId.
   *
   * @param studentId the id of the student that is enrolled in the course.
   * @param courseId  the id of the course that the student is enrolled in.
   * @return enrollment metric.
   */
  @GetMapping(path = "{studentId}-{courseId}")
  public ResponseEntity<EnrollmentMetricResponse> getEnrollmentMetric(
      @PathVariable Long studentId,
      @PathVariable Long courseId) {
    EnrollmentMetricResponse enrollmentMetricResponse =
        enrollmentMetricService.getEnrollmentMetric(studentId, courseId);
    return new ResponseEntity<>(enrollmentMetricResponse, HttpStatus.OK);
  }
}