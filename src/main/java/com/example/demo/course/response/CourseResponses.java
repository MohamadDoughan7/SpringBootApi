package com.example.demo.course.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * This class represents the list of courses that will be displayed in the response.
 */
@AllArgsConstructor
@Data
public class CourseResponses {

  private List<GetCourseResponse> courses;
}