package com.example.demo.course.enrollment;

import com.example.demo.course.CourseEntity;
import com.example.demo.course.CourseRepository;
import com.example.demo.course.enrollment.exceptions.CourseCapacityExceededException;
import com.example.demo.course.enrollment.exceptions.EnrollmentAlreadyExistsException;
import com.example.demo.course.enrollment.exceptions.EnrollmentNotFoundException;
import com.example.demo.course.enrollment.exceptions.MaximumEnrollmentReachedException;
import com.example.demo.course.enrollment.response.AddEnrollmentResponse;
import com.example.demo.course.enrollment.response.EnrollmentResponses;
import com.example.demo.course.enrollment.response.GetEnrollmentResponse;
import com.example.demo.course.exceptions.CourseNotFoundException;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

/**
 * This class is for performing the business logic for the methods within the enrollment package.
 */
@Service
@RequiredArgsConstructor
@Log4j2
public class EnrollmentService {

  private final ModelMapper modelMapper = new ModelMapper();
  private final EnrollmentRepository enrollmentRepository;
  private final CourseRepository courseRepository;

  /**
   * Retrieves the list of available enrollments.
   *
   * @return list of available enrollments.
   */
  public EnrollmentResponses getEnrollmentsByCourseId(long courseId) {
    /*
    Log info about displaying the list of enrollments.
     */
    log.info("List of existing enrollments has been displayed.");
    /*
    Get the list of enrollments from the service using the EnrollmentRepository interface,
    and then build the response.
     */
    return new EnrollmentResponses(
        enrollmentRepository.getEnrollmentsByCourseId(courseId).parallelStream()
            .map(enrollmentEntity -> modelMapper.map(enrollmentEntity, GetEnrollmentResponse.class))
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
  public AddEnrollmentResponse addNewEnrollment(Long studentId, Long courseId) {
    /*
     Check if the enrollmentEntity already exists in the database based on studentId and courseId.
     */
    if (enrollmentRepository.existsByIds(studentId, courseId)) {
      String errorMessage =
          "EnrollmentEntity already exists for studentId: " + studentId + " and courseId: "
              + courseId;
      throw new EnrollmentAlreadyExistsException(errorMessage);
    }
    /*
     Create a new EnrollmentEntity object
     */
    EnrollmentEntity enrollmentEntity = new EnrollmentEntity();
    enrollmentEntity.setStudentId(studentId);
    enrollmentEntity.setCourseId(courseId);
    /*
     Check if the number of students enrolled in this course exceeds its capacity.
     */
    if (enrollmentRepository.getEnrollmentCountByCourseId(courseId) >= getCourseCapacity(
        courseId)) {
      String errorMessage = "CourseEntity capacity exceeded for courseId: " + courseId;
      throw new CourseCapacityExceededException(errorMessage);
    }
    /*
     Check if the student is already enrolled in 6 courses.
     */
    int currentEnrollmentCount = enrollmentRepository.getEnrollmentCountByStudentId(studentId);

    if (currentEnrollmentCount >= 6) {
      /*
       You've reached the maximum enrollmentEntity limit for this student.
       */
      throw new MaximumEnrollmentReachedException(
          "Maximum enrollmentEntity limit reached for student with ID " + studentId);
    }
    /*
    Insert the enrollment in the database.
     */
    enrollmentRepository.insertEnrollment(enrollmentEntity);
    /*
     Log info about the new enrollmentEntity added.
     */
    log.info("New enrollmentEntity added: {}", enrollmentEntity);
    /*
     Map the newly inserted enrollmentEntity to an AddEnrollmentResponse object and return it.
     */
    return modelMapper.map(enrollmentEntity, AddEnrollmentResponse.class);
  }

  /**
   * Helper method to get the capacity of a course.
   */
  private int getCourseCapacity(Long courseId) {
    CourseEntity courseEntity = courseRepository.findById(courseId);
    if (courseEntity == null) {
      String errorMessage = "CourseEntity not found for courseId: " + courseId;
      throw new CourseNotFoundException(errorMessage);
    }
    return courseEntity.getCapacity();
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
    if (!enrollmentRepository.existsByIds(studentId, courseId)) {
      String errorMessage = "EnrollmentEntity with student id " + studentId + " and course id " +
          courseId + " does not exist.";
      throw new EnrollmentNotFoundException(errorMessage);
    }
    /*
     Delete the enrollment from the database using the EnrollmentRepository interface.
     */
    enrollmentRepository.deleteEnrollmentByIds(studentId, courseId);
    log.info("EnrollmentEntity with student id " + studentId + " and course id " +
        courseId + " has been deleted.");
  }
}