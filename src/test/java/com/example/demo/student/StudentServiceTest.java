package com.example.demo.student;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

import com.example.demo.student.exceptions.StudentEmailAlreadyTakenException;
import com.example.demo.student.exceptions.StudentNotFoundException;
import com.example.demo.student.request.AddStudentRequest;
import com.example.demo.student.response.AddStudentResponse;
import com.example.demo.student.response.GetStudentResponse;
import com.example.demo.student.response.StudentResponses;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * This class is for testing the methods of the StudentService class.
 */
public class StudentServiceTest {

  @InjectMocks
  private StudentService studentService;

  @Mock
  private StudentRepository studentRepository;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  /**
   * Testing getStudent method.
   */
  @Test
  public void testGetStudents() {
    /*
     Arrange
     */
    List<StudentEntity> studentEntities = new ArrayList<>();
    studentEntities.add(createStudentEntity(
        1L, "John", "john@example.com"
        , LocalDate.of(1990, 1, 1)));
    when(studentRepository.getAllStudents()).thenReturn(studentEntities);

    /*
     Act
     */
    StudentResponses studentResponses = studentService.getStudents();

    /*
     Assert
     */
    assertEquals(1, studentResponses.getStudents().size());
    GetStudentResponse response = studentResponses.getStudents().get(0);
    assertEquals(1L, response.getId());
    assertEquals("John", response.getName());
    assertEquals("john@example.com", response.getEmail());
    assertEquals(LocalDate.of(1990, 1, 1), response.getDob());
  }


  /**
   * Testing add new student method.
   */
  @Test
  public void testAddNewStudent() {
    // Arrange
    AddStudentRequest request = new AddStudentRequest();
    request.setName("John");
    request.setDob(LocalDate.of(1990, 1, 1));
    request.setEmail("john@example.com");

    when(studentRepository.findStudentByEmail("john@example.com")).thenReturn(null);

    /*
     Use doAnswer to perform an action when insertStudent is called
     */
    doAnswer(invocation -> {
      StudentEntity entity = invocation.getArgument(0);
      entity.setId(1L); // Set an ID for the entity, as if it was added to the database
      return null; // Return null since insertStudent returns void
    }).when(studentRepository).insertStudent(any(StudentEntity.class));

    /*
     Act
     */
    AddStudentResponse addStudentResponse = studentService.addNewStudent(
        request.getName(), request.getDob(), request.getEmail());

    /*
     Assert
     */
    assertEquals(1L, addStudentResponse.getId());
    assertEquals("John", addStudentResponse.getName());
    assertEquals("john@example.com", addStudentResponse.getEmail());
  }


  /**
   * Testing the studentEmailAlreadyExists exception in the add new student method.
   */
  @Test
  public void testAddNewStudentWithExistingEmail() {
    /*
     Arrange
     */
    AddStudentRequest request = new AddStudentRequest();
    request.setName("John");
    request.setDob(LocalDate.of(1990, 1, 1));
    request.setEmail("john@example.com");

    when(studentRepository.findStudentByEmail("john@example.com"))
        .thenReturn(createStudentEntity(1L, "Another Student", "john@example.com"
            , LocalDate.of(1991, 2, 2)));

    /*
     Act and Assert
     */
    assertThrows(StudentEmailAlreadyTakenException.class,
        () -> studentService.addNewStudent(request.getName(), request.getDob(),
            request.getEmail()));
  }

  /**
   * Testing the delete student  by id method.
   */
  @Test
  public void testDeleteStudentById() {
    /*
     Arrange
     */
    Long studentId = 1L;
    when(studentRepository.existsById(studentId)).thenReturn(true);

    /*
     Act
     */
    assertDoesNotThrow(() -> studentService.deleteStudentById(studentId));
  }

  /**
   * Testing student does not exist exception in the delete student method.
   */
  @Test
  public void testDeleteNonExistentStudent() {
    /*
     Arrange
     */
    Long studentId = 1L;
    when(studentRepository.existsById(studentId)).thenReturn(false);

    /*
     Act and Assert
     */
    assertThrows(StudentNotFoundException.class, () -> studentService.deleteStudentById(studentId));
  }

  /**
   * Testing te updateStudentById method.
   */
  @Test
  public void testUpdateStudentById() {
    /*
     Arrange
     */
    Long studentId = 1L;
    String updatedName = "Updated Name";
    String updatedEmail = "updated.email@example.com";
    LocalDate updatedDob = LocalDate.of(1995, 5, 5);
    StudentEntity studentEntity = createStudentEntity
        (studentId, "John", "john@example.com",
            LocalDate.of(1990, 1, 1));

    when(studentRepository.findById(studentId)).thenReturn(studentEntity);

    /*
     Act
     */
    assertDoesNotThrow(
        () -> studentService.updateStudentById(studentId, updatedName, updatedEmail, updatedDob));

    /*
     Assert
     */
    assertEquals(updatedName, studentEntity.getName());
    assertEquals(updatedEmail, studentEntity.getEmail());
    assertEquals(updatedDob, studentEntity.getDob());
  }

  /**
   * Testing the student does not exist exception in the update student method.
   */
  @Test
  public void testUpdateNonExistentStudent() {
    /*
     Arrange
     */
    Long studentId = 1L;
    String updatedName = "Updated Name";
    String updatedEmail = "updated.email@example.com";
    LocalDate updatedDob = LocalDate.of(1995, 5, 5);

    when(studentRepository.findById(studentId)).thenReturn(null);

    /*
     Act and Assert
     */
    assertThrows(StudentNotFoundException.class,
        () -> studentService.updateStudentById(studentId, updatedName, updatedEmail, updatedDob));
  }

  /**
   * Helper method to create a student.
   *
   * @param id    of the student that will be created
   * @param name  of the student that will be created
   * @param email of the student that will be created
   * @param dob   of the student that will be created
   * @return the newly created student
   */
  private StudentEntity createStudentEntity(Long id, String name, String email, LocalDate dob) {
    StudentEntity studentEntity = new StudentEntity();
    studentEntity.setId(id);
    studentEntity.setName(name);
    studentEntity.setEmail(email);
    studentEntity.setDob(dob);
    return studentEntity;
  }
}