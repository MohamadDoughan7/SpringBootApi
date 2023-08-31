package com.example.demo.enrollmentmetric;

import com.example.demo.course.enrollment.exceptions.EnrollmentNotFoundException;
import com.example.demo.enrollmentmetric.response.EnrollmentMetricResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * This class is for performing the business logic for the methods within the enrollmentMetric
 * package.
 */
@Service
@RequiredArgsConstructor
@Log4j2
public class EnrollmentMetricService {

  private final ModelMapper modelMapper = new ModelMapper();
  private final EnrollmentMetricRepository enrollmentMetricRepository;

  /**
   * This method is for displaying the enrollment metric.
   *
   * @param studentId the id of the student with the specified id.
   * @param courseId  the id of the course with thw specified id.
   * @return enrollment metric.
   */
  @Transactional(rollbackFor = Exception.class)
  public EnrollmentMetricResponse getEnrollmentMetric(Long studentId, Long courseId) {
    EnrollmentMetric enrollmentMetric =
        enrollmentMetricRepository.getEnrollmentMetric(studentId, courseId);
    /*
    Check if the enrollment with the entered studentId and courseId exists.
     */
    if (enrollmentMetric == null) {
      throw new EnrollmentNotFoundException("Enrollment data not found for studentId: "
          + studentId + " and courseId: " + courseId);
    }
    /*
    Preparing the response.
     */
    EnrollmentMetric enrollment = new EnrollmentMetric();
    enrollment.setStudentId(enrollment.getStudentId());
    enrollment.setStudentName(enrollment.getStudentName());
    enrollment.setCourseId(enrollment.getCourseId());
    enrollment.setCourseName(enrollment.getCourseName());
    enrollment.setCourseCode(enrollment.getCourseCode());
    enrollment.setTeacherId(enrollment.getTeacherId());
    enrollment.setTeacherName(enrollment.getTeacherName());
    /*
    Log info about successful display of the metric.
     */
    log.info("Metric of enrollment with studentId " + studentId + " and with courseId " +
        courseId + " has been displayed.");
    /*
    Map the enrollmentMetric to an EnrollmentMetric response and generating the response.
     */
    return modelMapper.map(enrollmentMetric, EnrollmentMetricResponse.class);
  }
}