package com.example.demo.distribution;

import com.example.demo.distribution.exceptions.DistributionAlreadyExistsException;
import com.example.demo.distribution.exceptions.DistributionNotFoundException;
import com.example.demo.distribution.exceptions.MaximumNumberOfCoursesReachedException;
import com.example.demo.distribution.exceptions.MaximumNumberOfTeachersReachedException;
import com.example.demo.distribution.response.DistributionResponse;
import com.example.demo.distribution.response.DistributionResponseList;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

/**
 * This class is for performing the business logic for the methods within the distribution package.
 */
@Service
@RequiredArgsConstructor
@Log4j2
public class DistributionService {


  private final DistributionMapper distributionMapper;

  /**
   * Retrieves the list of available distributions.
   *
   * @return list of available distributions.
   */
  public DistributionResponseList getDistribution() {
    /*
    Log info about displaying the list of distributions.
     */
    log.info("List of existing distributions has been displayed.");

    /*
    Get the list of distributions from the service using the DistributionMapper interface,
    and then build the response.
     */
    return new DistributionResponseList(
        distributionMapper.getAllDistributions().stream()
            .map(distribution -> new DistributionResponse(
                distribution.getTeacherId(),
                distribution.getCourseId()))
            .collect(Collectors.toList())
    );
  }


  /**
   * Adds a new distribution and saves it in the database.
   *
   * @param teacherId the id of the teacher that is assigned to teach the courses
   * @param courseId  the id of the course that the teacher is assigned to teach
   * @return the added distribution
   */

  public DistributionResponse addNewDistribution(Long teacherId, Long courseId) {
    /*
     Check if the distribution already exists in the database based on teacherId and courseId.
     */
    if (distributionMapper.existsByIds(teacherId, courseId)) {
      String errorMessage =
          "Distribution already exists for teacherId: " + teacherId + " and courseId: "
              + courseId;
      throw new DistributionAlreadyExistsException(errorMessage);
    }

    /*
     Check if the teacher is already teaching 6 courses.
     */
    int currentDistributionCount =
        distributionMapper.getDistributionCountByTeacherId(teacherId);

    if (currentDistributionCount >= 6) {
      /*
       You've reached the maximum number of courses for this teacher.
       */
      throw new MaximumNumberOfCoursesReachedException
          ("Maximum number of courses reached for teacher with ID " + teacherId);
    }


    /*
     Check if the course is taught by more than 3 teachers.
     */
    int currentDistributionCourseCount =
        distributionMapper.getDistributionCountByCourseId(courseId);

    if (currentDistributionCourseCount >= 3) {
      /*
       You've reached the maximum number of teachers for this course.
       */
      throw new MaximumNumberOfTeachersReachedException
          ("Maximum number of teachers limit reached for course with ID " + courseId);
    }

    /*
     Create a new Distribution object and insert it into the database using
      the DistributionMapper interface.
     */
    Distribution distribution = new Distribution(teacherId, courseId);

    distributionMapper.insertDistribution(distribution);

    /*
     Log info about the new distribution added.
     */
    log.info("New distribution added: {}", distribution);

    /*
     Map the newly inserted distribution to a DistributionResponse object and return it.
     */
    return new DistributionResponse(distribution.getTeacherId(),
        distribution.getCourseId());
  }


  /**
   * Deletes a Distribution from the database.
   *
   * @param teacherId the ID of the teacher of the teacher_distribution to be deleted
   * @param courseId  the ID of the course of the Distribution to be deleted
   * @throws DistributionNotFoundException if the Distribution with the given IDs does not exist
   */
  public void deleteDistributionByIds(Long teacherId, Long courseId) {
    /*
     Check if the distribution exists in the database based on the provided ids.
     */
    if (!distributionMapper.existsByIds(teacherId, courseId)) {
      String errorMessage = "Distribution with teacher id " + teacherId + " and course id "
          + courseId + " does not exist.";
      throw new DistributionNotFoundException(errorMessage);
    }
    /*
     Delete the distribution from the database using the DistributionMapper interface.
     */
    distributionMapper.deleteDistributionByIds(teacherId, courseId);
    log.info("Distribution with teacher id " + teacherId + " and course id " +
        courseId + " has been deleted.");
  }

}
