package com.example.demo.student.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * This class represents the list of students that will be displayed in the response.
 */
@AllArgsConstructor
@Data
public class StudentResponseList {

  private List<StudentResponse> students;

}

