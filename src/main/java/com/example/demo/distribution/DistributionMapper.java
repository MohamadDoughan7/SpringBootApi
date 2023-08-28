package com.example.demo.distribution;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * This interface is a mapper to handle ORM for the distribution relation.
 */
@Repository
@Mapper
public interface DistributionMapper {

  /**
   * Defining the getAllDistributions method.
   *
   * @return list of distribution.
   */
  List<Distribution> getAllDistributions();

  /**
   * This method is for finding a distribution based on the teacherId and the courseId.
   *
   * @param teacherId of the needed teacher.
   * @param courseId of the course the teacher is assigned to teach.
   * @return teacher_distribution with the entered teacherId and courseId.
   */
  Distribution findByIds(Long teacherId, Long courseId);


  /**
   * Defining the insertDistribution method.
   *
   * @param distribution to be added to the database.
   */
  void insertDistribution(Distribution distribution);


  /**
   * Defining the deleteDistribution method.
   *
   * @param teacherId in the distribution to be deleted.
   * @param courseId in the distribution to be deleted.
   */
  void deleteDistributionByIds(Long teacherId, Long courseId);

  /**
   * This method is for checking if the distribution with the entered ids is available in the database.
   *
   * @param teacherId of the needed distribution to be found.
   * @param courseId of the needed distribution to be found.
   * @return true for existing distribution and false for a non-existing one.
   */
  boolean existsByIds(Long teacherId, Long courseId);

  /**
   * Get the count of teacher distributions for a specific teacher.
   *
   * @param teacherId the ID of the course
   * @return the count of distributions
   */
  int getDistributionCountByTeacherId(Long teacherId);

  /**
   * Get the count of distributions for a specific teacher.
   *
   * @param courseId the ID of the course
   * @return the count of enrollments
   */
  int getDistributionCountByCourseId(Long courseId);

}
