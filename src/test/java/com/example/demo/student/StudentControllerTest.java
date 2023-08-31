package com.example.demo.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.when;

import com.example.demo.student.request.AddStudentRequest;
import com.example.demo.student.request.UpdateStudentRequest;
import com.example.demo.student.response.AddStudentResponse;
import com.example.demo.student.response.DeleteStudentResponse;
import com.example.demo.student.response.GetStudentResponse;
import com.example.demo.student.response.StudentResponses;
import com.example.demo.student.response.UpdateStudentResponse;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * This class is for testing th method of the StudentController class.
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
   * Testing the get students method.
   */
  @Test
  void testGetStudents() {
    /*
     Arrange
     */
    List<GetStudentResponse> studentResponsesList = Collections.emptyList();
    when(studentService.getStudents()).thenReturn(new StudentResponses(studentResponsesList));

    /*
     Act
     */
    ResponseEntity<StudentResponses> responseEntity = studentController.getStudents();

    /*
     Assert
     */
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals(studentResponsesList,
        Objects.requireNonNull(responseEntity.getBody()).getStudents());
  }

  /**
   * Testing the add new student method.
   */
  @Test
  void testAddNewStudent() {
    /*
     Arrange
     */
    AddStudentRequest addStudentRequest = new AddStudentRequest();
    addStudentRequest.setName("John Doe");
    addStudentRequest.setDob(LocalDate.of(1990, 1, 1));
    addStudentRequest.setEmail("john.doe@example.com");

    AddStudentResponse addStudentResponse = new AddStudentResponse();
    addStudentResponse.setId(1L);
    addStudentResponse.setName(addStudentRequest.getName());
    addStudentResponse.setDob(addStudentRequest.getDob());
    addStudentResponse.setEmail(addStudentRequest.getEmail());
    addStudentResponse.getAge();

    when(studentService.addNewStudent(anyString(), any(LocalDate.class), anyString()))
        .thenReturn(addStudentResponse);

    /*
     Act
     */
    ResponseEntity<AddStudentResponse> responseEntity = studentController.addNewStudent(
        addStudentRequest);

    /*
     Assert
     */
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals(addStudentResponse, responseEntity.getBody());
  }

  /**
   * Testing the delete student method.
   */
  @Test
  void testDeleteStudent() {
    /*
     Arrange
     */
    long studentId = 1L;
    doNothing().when(studentService).deleteStudentById(studentId);

    /*
     Act
     */
    ResponseEntity<DeleteStudentResponse> responseEntity = studentController.deleteStudent(
        studentId);

    /*
     Assert
     */
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals(studentId, Objects.requireNonNull(responseEntity.getBody()).getId());
  }

  /**
   * Testing the update student method.
   */
  @Test
  void testUpdateStudent() {
    /*
     Arrange
     */
    long studentId = 1L;
    UpdateStudentRequest updateStudentRequest = new UpdateStudentRequest();
    updateStudentRequest.setName("Updated Name");
    updateStudentRequest.setEmail("updated.email@example.com");
    updateStudentRequest.setDob(LocalDate.of(2000, 1, 1));

    doNothing().when(studentService).updateStudentById(
        eq(studentId), anyString(), anyString(), any(LocalDate.class));

    /*
     Act
     */
    ResponseEntity<UpdateStudentResponse> responseEntity = studentController.updateStudent(
        studentId, updateStudentRequest);

    /*
     Assert
     */
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals(studentId, Objects.requireNonNull(responseEntity.getBody()).getId());
  }
}