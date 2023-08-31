package com.example.demo.teacher.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * This class represents the list of students that will be displayed in the get response.
 */
@AllArgsConstructor
@Data
public class TeacherResponses {

  private List<GetTeacherResponse> teachers;
}