package com.example.demo.distribution.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class represents the distribution response.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DistributionResponse {

  /*
   * This is the id of the teacher that is assigned to teach the courses.
   * It is a partial primary key.
   */
  private Long teacherId;

  /*
   * This is the id of the course that teachers are assigned to teach.
   * It is a partial primary key.
   */
  private Long courseId;
}
