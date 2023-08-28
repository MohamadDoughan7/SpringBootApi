package com.example.demo.enrollment;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * This interface is a mapper to handle ORM for the enrollment relation.
 */
@Repository
@Mapper
public interface EnrollmentMapper {

  /**
   * Defining the getAllEnrollments method.
   *
   * @return list of enrollments.
   */
  List<Enrollment> getAllEnrollments();

  /**
   * This method is for finding an enrollment based on teh studentId and the courseId.
   *
   * @param studentId of the needed student.
   * @param courseId of the course the student is enrolled in.
   * @return enrollment with the entered studentId and courseId.
   */
  Enrollment findByIds(Long studentId, Long courseId);


  /**
   * Defining the insertEnrollment method.
   *
   * @param enrollment to be added to the database.
   */
  void insertEnrollment(Enrollment enrollment);


  /**
   * Defining the deleteEnrollment method.
   *
   * @param studentId in the enrollment to be deleted.
   * @param courseId in the enrollment to be deleted.
   */
  void deleteEnrollmentByIds(Long studentId, Long courseId);

  /**
   * This method is for checking if the enrollment with the entered ids is available in the database.
   *
   * @param studentId of the needed enrollment to be found.
   * @param courseId of the needed enrollment to be found.
   * @return true for existing enrollment and false for a non-existing enrollment.
   */
  boolean existsByIds(Long studentId, Long courseId);

  /**
   * Get the count of enrollments for a specific course.
   *
   * @param courseId the ID of the course
   * @return the count of enrollments
   */
  int getEnrollmentCountByCourseId(Long courseId);


  /**
   * Get the count of enrollments for a specific student.
   *
   * @param studentId the ID of the course
   * @return the count of enrollments
   */
  int getEnrollmentCountByStudentId(Long studentId);
}
