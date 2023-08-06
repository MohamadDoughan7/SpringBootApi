package com.example.demo.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.demo.student.request.StudentRequest;
import com.example.demo.student.response.StudentResponse;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * This class if for testing the methods of the StudentController class.
 */
class StudentControllerTest {

  @Mock
  private StudentService studentService;

  @InjectMocks
  private StudentController studentController;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  /**
   * Testing the get mapping method.
   */
  @Test
  void testGetStudents() {
    /*
     Mocking the getStudents method of the StudentService to return a list of students.
     */
    List<StudentResponse> students = new ArrayList<>();
    students.add(new StudentResponse(1L, "John Doe",
        LocalDate.of(1995, 8, 10), "john.doe@example.com", 28));
    students.add(new StudentResponse(2L, "Jane Smith",
        LocalDate.of(1998, 5, 20), "jane.smith@example.com", 25));
    when(studentService.getStudents()).thenReturn(students);

    /*
     Calling the method to be tested.
     */
    ResponseEntity<List<StudentResponse>> responseEntity = studentController.getStudents();

    /*
     Verifying the result.
     */
    assertNotNull(responseEntity);
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertNotNull(responseEntity.getBody());
    assertEquals(2, responseEntity.getBody().size());
    assertEquals("John Doe", responseEntity.getBody().get(0).getName());
    assertEquals("jane.smith@example.com", responseEntity.getBody().get(1).getEmail());

    /*
      Verifying that the getStudents method of the StudentService is called once.
     */
    verify(studentService, times(1)).getStudents();
  }

  /**
   * Testing the post mapping method.
   */
  @Test
  void testCreateNewStudent() {
    /*
     Mocking the addNewStudent method of the StudentService to return a new student.
     */
    StudentResponse newStudent = new StudentResponse(1L, "John Doe",
        LocalDate.of(1995, 8, 10), "john.doe@example.com", 28);
    when(studentService.addNewStudent(anyString(), any(), anyString())).thenReturn(newStudent);

    /*
     Creating a StudentRequest object.
     */
    StudentRequest studentRequest = new StudentRequest();
    studentRequest.setName("John Doe");
    studentRequest.setDob(LocalDate.of(1995, 8, 10));
    studentRequest.setEmail("john.doe@example.com");

    /*
     Calling the method to be tested.
     */
    ResponseEntity<StudentResponse> responseEntity = studentController.
        createNewStudent(studentRequest);

    /*
     Verifying the result.
     */
    assertNotNull(responseEntity);
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertNotNull(responseEntity.getBody());
    assertEquals("John Doe", responseEntity.getBody().getName());
    assertEquals("john.doe@example.com", responseEntity.getBody().getEmail());

    /*
     Verifying that the addNewStudent method of the StudentService is called once.
     */
    verify(studentService, times(1)).
        addNewStudent(eq("John Doe"), any(), eq("john.doe@example.com"));
  }

  /**
   * Testing the delete mapping method.
   */
  @Test
  void testDeleteStudent() {
    Long studentId = 1L;

    /*
     Calling the method to be tested.
     */
    ResponseEntity<String> responseEntity = studentController.deleteStudent(studentId);

    /*
     Verifying the result.
     */
    assertNotNull(responseEntity);
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals("Student deleted successfully", responseEntity.getBody());

    /*
     Verifying that the deleteStudentById method of the StudentService is called once.
     */
    verify(studentService, times(1)).deleteStudentById(studentId);
  }

  /**
   * Testing the put mapping method.
   */
  @Test
  void testUpdateStudent() {
    Long studentId = 1L;
    String name = "Updated Name";
    String email = "updated.email@example.com";

    /*
     Calling the method to be tested.
     */
    ResponseEntity<String> responseEntity = studentController.updateStudent(studentId, name, email);

    /*
     Verifying the result.
     */
    assertNotNull(responseEntity);
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals("Student updated successfully", responseEntity.getBody());

    /*
     Verifying that the updateStudentById method of the StudentService is called once.
     */
    verify(studentService, times(1)).updateStudentById(studentId, name, email);
  }
}

