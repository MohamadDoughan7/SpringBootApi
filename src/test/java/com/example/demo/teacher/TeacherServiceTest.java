package com.example.demo.teacher;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.demo.teacher.exceptions.TeacherEmailAlreadyTakenException;
import com.example.demo.teacher.exceptions.TeacherNotFoundException;
import com.example.demo.teacher.response.AddTeacherResponse;
import com.example.demo.teacher.response.GetTeacherResponse;
import com.example.demo.teacher.response.TeacherResponses;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * This class is for testing the methods of the TeacherService class.
 */
class TeacherServiceTest {

  private TeacherService teacherService;

  @Mock
  private TeacherRepository teacherRepository;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    teacherService = new TeacherService(teacherRepository);
  }

  /**
   * Testing the getTeachers method.
   */
  @Test
  void testGetTeachers() {
    /*
     Mock data for teacher entities
     */
    List<TeacherEntity> teacherEntities = Collections.singletonList(
        new TeacherEntity
            (1L, "John Doe", LocalDate.of(1980, 1, 1),
                "john.doe@example.com", "Math")
    );

    /*
     Mock behavior of teacherRepository
     */
    when(teacherRepository.getAllTeachers()).thenReturn(teacherEntities);

    /*
     Call the service method
     */
    TeacherResponses teacherResponses = teacherService.getTeachers();

    /*
     Verify the result
     */
    assertNotNull(teacherResponses);
    assertEquals(1, teacherResponses.getTeachers().size());

    GetTeacherResponse response = teacherResponses.getTeachers().get(0);
    assertEquals(1L, response.getId());
    assertEquals("John Doe", response.getName());
    assertEquals("john.doe@example.com", response.getEmail());
    assertEquals("Math", response.getSubject());
  }

  /**
   * Testing the addTeacher method.
   */
  @Test
  void testAddTeacher() {
    /*
     Mock request data
     */
    String name = "John Doe";
    LocalDate dob = LocalDate.of(1980, 1, 1);
    String email = "john.doe@example.com";
    String subject = "Math";

    /*
     Mock behavior of teacherRepository
     */
    when(teacherRepository.findTeacherByEmail(anyString())).thenReturn(null);

    /*
     Mock data for teacher entity
     */
    TeacherEntity savedTeacher = new TeacherEntity(1L, name, dob, email, subject);

    /*
     Mock behavior of teacherRepository for void method
     */
    doNothing().when(teacherRepository).insertTeacher(any(TeacherEntity.class));

    /*
     Call the service method
     */
    AddTeacherResponse response = teacherService.addTeacher(name, dob, email, subject);

    /*
     Verify the result
     */
    assertNotNull(response);
    assertEquals(name, response.getName());
    assertEquals(dob, response.getDob());
    assertEquals(email, response.getEmail());
    assertEquals(subject, response.getSubject());
  }

  /**
   * Testing the EmailAlreadyTakenException in the addTeacher method.
   */
  @Test
  void testAddTeacherWithEmailTaken() {
    /*
     Mock request data
     */
    String name = "John Doe";
    LocalDate dob = LocalDate.of(1980, 1, 1);
    String email = "john.doe@example.com";
    String subject = "Math";

    /*
     Mock behavior of teacherRepository
     */
    when(teacherRepository.findTeacherByEmail(email)).thenReturn(new TeacherEntity());

    /*
     Verify that adding a teacher with a taken email throws an exception
     */
    assertThrows(TeacherEmailAlreadyTakenException.class, () ->
        teacherService.addTeacher(name, dob, email, subject));
  }

  /**
   * Testing the deleteTeacher method.
   */
  @Test
  void testDeleteTeacherById() {
    /*
     Mock teacher ID
     */
    Long teacherId = 1L;

    /*
     Mock behavior of teacherRepository
     */
    when(teacherRepository.existsById(teacherId)).thenReturn(true);

    /*
     Call the service method
     */
    teacherService.deleteTeacherById(teacherId);

    /*
     Verify that deleteTeacherById method of teacherRepository is called
     */
    verify(teacherRepository, times(1)).deleteTeacherById(teacherId);
  }

  /**
   * Testing the TeacherNotFoundException in the deleteTeacher method.
   */
  @Test
  void testDeleteTeacherByIdNotFound() {
    /*
     Mock teacher ID
     */
    Long teacherId = 1L;

    /*
     Mock behavior of teacherRepository
     */
    when(teacherRepository.existsById(teacherId)).thenReturn(false);

    /*
     Verify that deleting a non-existing teacher ID throws an exception
     */
    assertThrows(TeacherNotFoundException.class, () -> teacherService.deleteTeacherById(teacherId));
  }

  /**
   * Testing the updateTeacherById method.
   */
  @Test
  void testUpdateTeacherById() {
    /*
     Mock teacher data
     */
    Long teacherId = 1L;
    String name = "John Doe";
    LocalDate dob = LocalDate.of(1980, 1, 1);
    String email = "john.doe@example.com";
    String subject = "Math";

    /*
     Mock behavior of teacherRepository
     */
    when(teacherRepository.findById(teacherId)).thenReturn(new TeacherEntity());

    /*
     Call the service method
     */
    teacherService.updateTeacherById(teacherId, name, email, dob, subject);

    /*
     Verify that updateTeacher method of teacherRepository is called
     */
    verify(teacherRepository, times(1)).updateTeacher(any(TeacherEntity.class));
  }

  /**
   * Testing the TeacherNotFoundException in the updateTeacher method.
   */
  @Test
  void testUpdateTeacherByIdNotFound() {
    /*
     Mock teacher data
     */
    Long teacherId = 1L;
    String name = "John Doe";
    LocalDate dob = LocalDate.of(1980, 1, 1);
    String email = "john.doe@example.com";
    String subject = "Math";

    /*
     Mock behavior of teacherRepository
     */
    when(teacherRepository.findById(teacherId)).thenReturn(null);

    /*
     Verify that updating a non-existing teacher ID throws an exception
     */
    assertThrows(TeacherNotFoundException.class,
        () -> teacherService.updateTeacherById(teacherId, name, email, dob, subject));
  }
}