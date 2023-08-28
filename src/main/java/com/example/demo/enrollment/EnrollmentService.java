package com.example.demo.enrollment;

import com.example.demo.course.Course;
import com.example.demo.course.CourseMapper;
import com.example.demo.course.exceptions.CourseNotFoundException;
import com.example.demo.enrollment.exceptions.CourseCapacityExceededException;
import com.example.demo.enrollment.exceptions.EnrollmentAlreadyExistsException;
import com.example.demo.enrollment.exceptions.EnrollmentNotFoundException;
import com.example.demo.enrollment.exceptions.MaximumEnrollmentReachedException;
import com.example.demo.enrollment.response.EnrollmentResponse;
import com.example.demo.enrollment.response.EnrollmentResponseList;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

/**
 * This class is for performing the business logic for the methods within the enrollment package.
 */
@Service
@RequiredArgsConstructor
@Log4j2
public class EnrollmentService {


  private final EnrollmentMapper enrollmentMapper;
  private final CourseMapper courseMapper;

  /**
   * Retrieves the list of available enrollments.
   *
   * @return list of available enrollments.
   */

  public EnrollmentResponseList getEnrollments() {
    /*
    Log info about displaying the list of enrollments.
     */
    log.info("List of existing enrollments has been displayed.");

    /*
    Get the list of enrollments from the service using the EnrollmentMapper interface,
    and then build the response.
     */
    return new EnrollmentResponseList(
        enrollmentMapper.getAllEnrollments().stream()
            .map(enrollment -> new EnrollmentResponse(
                enrollment.getStudentId(),
                enrollment.getCourseId()))
            .collect(Collectors.toList())
    );
  }


  /**
   * Adds a new enrollment and saves it in the database.
   *
   * @param studentId the id of the student that is enrolled in the courses
   * @param courseId  the id of the course that the students are enrolled in
   * @return the added enrollment
   */


  public EnrollmentResponse addNewEnrollment(Long studentId, Long courseId) {
    /*
     Check if the enrollment already exists in the database based on studentId and courseId.
     */
    if (enrollmentMapper.existsByIds(studentId, courseId)) {
      String errorMessage =
          "Enrollment already exists for studentId: " + studentId + " and courseId: "
              + courseId;
      throw new EnrollmentAlreadyExistsException(errorMessage);
    }

    /*
     Create a new Enrollment object and insert it into the database using the EnrollmentMapper interface.
     */
    Enrollment enrollment = new Enrollment(studentId, courseId);

    /*
     Check if the number of students enrolled in this course exceeds its capacity.
     */
    if (enrollmentMapper.getEnrollmentCountByCourseId(courseId) >= getCourseCapacity(courseId)) {
      String errorMessage = "Course capacity exceeded for courseId: " + courseId;
      throw new CourseCapacityExceededException(errorMessage);
    }

    /*
     Check if the student is already enrolled in 6 courses.
     */
    int currentEnrollmentCount = enrollmentMapper.getEnrollmentCountByStudentId(studentId);

    if (currentEnrollmentCount >= 6) {
      /*
       You've reached the maximum enrollment limit for this student.
       */
      throw new MaximumEnrollmentReachedException("Maximum enrollment limit reached for student with ID " + studentId);
    }


    enrollmentMapper.insertEnrollment(enrollment);

    /*
     Log info about the new enrollment added.
     */
    log.info("New enrollment added: {}", enrollment);

    /*
     Map the newly inserted enrollment to an EnrollmentResponse object and return it.
     */
    return new EnrollmentResponse(enrollment.getStudentId(), enrollment.getCourseId());
  }

  /**
   * Helper method to get the capacity of a course.
   */
  private int getCourseCapacity(Long courseId) {
    Course course = courseMapper.findById(courseId);
    if (course == null) {
      String errorMessage = "Course not found for courseId: " + courseId;
      throw new CourseNotFoundException(errorMessage);
    }
    return course.getCapacity();
  }


  /**
   * Deletes amn enrollment from the database.
   *
   * @param studentId the ID of the student of the enrollment to be deleted
   * @param courseId  the ID of the course of the enrollment to be deleted
   * @throws EnrollmentNotFoundException if the enrollment with the given IDs does not exist
   */
  public void deleteEnrollmentByIds(Long studentId, Long courseId) {
    /*
     Check if the enrollment exists in the database based on the provided ids.
     */
    if (!enrollmentMapper.existsByIds(studentId, courseId)) {
      String errorMessage = "Enrollment with student id " + studentId + " and course id " +
          courseId + " does not exist.";
      throw new EnrollmentNotFoundException(errorMessage);
    }
    /*
     Delete the enrollment from the database using the EnrollmentMapper interface.
     */
    enrollmentMapper.deleteEnrollmentByIds(studentId, courseId);
    log.info("Enrollment with student id " + studentId + " and course id " +
        courseId + " has been deleted.");
  }

}
