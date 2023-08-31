package com.example.demo.teacher;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.demo.teacher.request.AddTeacherRequest;
import com.example.demo.teacher.request.UpdateTeacherRequest;
import com.example.demo.teacher.response.AddTeacherResponse;
import com.example.demo.teacher.response.GetTeacherResponse;
import com.example.demo.teacher.response.TeacherResponses;
import com.example.demo.teacher.response.UpdateTeacherResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * This class is for testing the methods of the TecaherController class.
 */
public class TeacherControllerTest {

  @Mock
  private TeacherService teacherService;

  @Mock
  private Validator validator;

  private TeacherController teacherController;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    teacherController = new TeacherController(teacherService);
  }

  /**
   * Testing the getTeacher method.
   */
  @Test
  void testGetTeachers() {
    /*
     Arrange
     */
    List<GetTeacherResponse> teacherResponsesList = Collections.emptyList();
    when(teacherService.getTeachers()).thenReturn(new TeacherResponses(teacherResponsesList));

    /*
     Act
     */
    ResponseEntity<TeacherResponses> responseEntity = teacherController.getTeachers();

    /*
     Assert
     */
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals(teacherResponsesList,
        Objects.requireNonNull(responseEntity.getBody()).getTeachers());
  }

  /**
   * Testing the add new teacher method.
   */
  @Test
  void testAddNewTeacher() {
    /*
     Arrange
     */
    AddTeacherRequest addTeacherRequest = new AddTeacherRequest();
    addTeacherRequest.setName("John Doe");
    addTeacherRequest.setDob(LocalDate.of(1990, 1, 1));
    addTeacherRequest.setEmail("john.doe@example.com");
    addTeacherRequest.setSubject("Math");

    AddTeacherResponse addTeacherResponse = new AddTeacherResponse();
    addTeacherResponse.setId(1L);
    addTeacherResponse.setName(addTeacherRequest.getName());
    addTeacherResponse.setDob(addTeacherRequest.getDob());
    addTeacherResponse.setEmail(addTeacherRequest.getEmail());
    addTeacherResponse.setSubject(addTeacherRequest.getSubject());

    when(teacherService.addTeacher(anyString(), any(LocalDate.class), anyString(), anyString()))
        .thenReturn(addTeacherResponse);

    /*
     Act
     */
    ResponseEntity<AddTeacherResponse> responseEntity = teacherController.addNewTeacher(
        addTeacherRequest);

    /*
     Assert
     */
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals(addTeacherResponse, responseEntity.getBody());
  }

  /**
   * Testing the failed validation in the add new teacher method.
   */
  @Test
  void testAddNewTeacherValidationFailed() {
    com.example.demo.teacher.request.AddTeacherRequest request = new com.example.demo.teacher.request.AddTeacherRequest(
        null, LocalDate.of(1980, 1, 1), "john.doe@example.com", "Math");

    when(validator.validate(any())).thenReturn(createMockConstraintViolation());

    teacherController.addNewTeacher(request);

    verify(teacherService, never()).addTeacher(anyString(), any(LocalDate.class), anyString(),
        anyString());
  }

  /**
   * Testing the delete teacher method.
   */
  @Test
  void testDeleteTeacher() {
    Long teacherId = 1L;
    teacherController.deleteTeacher(teacherId);

    verify(teacherService).deleteTeacherById(teacherId);
  }


  /**
   * Testing the update teacher method.
   */
  @Test
  void testUpdateTeacher() {
    UpdateTeacherRequest request = new UpdateTeacherRequest(
        "Updated Name", LocalDate.of(1990, 1, 1),
        "updated.email@example.com", "Physics");

    ResponseEntity<UpdateTeacherResponse> responseEntity =
        teacherController.updateTeacher(1L, request);

    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertNotNull(responseEntity.getBody());
    assertEquals(1L, responseEntity.getBody().getId());
  }

  /**
   * Method to create mock constraint violations
   *
   * @return set of constraint violations.
   */
  private Set createMockConstraintViolation() {
    return Set.of(
        mock(ConstraintViolation.class),
        mock(ConstraintViolation.class)
    );
  }
}