package com.example.demo.course.enrollment.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * This class represents the list of enrollments that will be displayed in the response.
 */
@AllArgsConstructor
@Data
public class EnrollmentResponses {

  private List<GetEnrollmentResponse> enrollments;
}