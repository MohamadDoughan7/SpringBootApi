package com.example.demo.enrollmentmetric;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * This interface is a mapper to handle ORM for the enrollment relation.
 */
@Repository
@Mapper
public interface EnrollmentMetricRepository {
  EnrollmentMetric getEnrollmentMetric (Long studentId, Long courseId);
}