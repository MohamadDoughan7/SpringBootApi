package com.example.demo.course.teachercourse.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * This class represents the list of distributions that will be displayed in the response.
 */
@AllArgsConstructor
@Data
public class TeacherCourseResponses {

  private List<GetTeacherCourseResponse> distributions;

}