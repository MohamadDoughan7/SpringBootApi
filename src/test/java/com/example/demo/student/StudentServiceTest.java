package com.example.demo.student;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.demo.student.exceptions.EmailAlreadyTakenException;
import com.example.demo.student.exceptions.InvalidDOBException;
import com.example.demo.student.exceptions.InvalidEmailFormatException;
import com.example.demo.student.exceptions.InvalidNameException;
import com.example.demo.student.exceptions.StudentNotFoundException;
import com.example.demo.student.response.StudentResponse;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * This class if for testing the methods of the StudentService class.
 */
class StudentServiceTest {

  @Mock
  private StudentMapper studentMapper;

  @InjectMocks
  private StudentService studentService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  /**
   * Testing the getStudent method.
   */
  @Test
  void testGetStudents() {
    /*
      Mocking the getAllStudents method of the StudentMapper to return a list of students.
     */
    List<Student> students = new ArrayList<>();
    students.add(new Student("John Doe",
        LocalDate.of(1995, 8, 10), "john.doe@example.com"));
    students.add(new Student("Jane Smith",
        LocalDate.of(1998, 5, 20), "jane.smith@example.com"));
    when(studentMapper.getAllStudents()).thenReturn(students);

    /*
     Calling the method to be tested.
     */
    List<StudentResponse> studentResponses = studentService.getStudents();

    /*
     Verifying the result.
     */
    assertNotNull(studentResponses);
    assertEquals(2, studentResponses.size());
    assertEquals("John Doe", studentResponses.get(0).getName());
    assertEquals("Jane Smith", studentResponses.get(1).getName());
    assertEquals("john.doe@example.com", studentResponses.get(0).getEmail());
    assertEquals("jane.smith@example.com", studentResponses.get(1).getEmail());

    /*
     Verifying that the getAllStudents method of the StudentMapper is called once.
     */
    verify(studentMapper, times(1)).getAllStudents();
  }

  /**
   * Test the successful output of the addNewStudent method.
   */
  @Test
  void testAddNewStudent() {
    /*
    Mocking the findStudentByEmail method of the StudentMapper to return null (email doesn't exist).
     */
    when(studentMapper.findStudentByEmail(any())).thenReturn(null);

    /*
     Mocking the insertStudent method of the StudentMapper to simulate successful insertion.
     */
    doNothing().when(studentMapper).insertStudent(any());

    /*
     Calling the method to be tested.
     */
    StudentResponse studentResponse = studentService.addNewStudent("John Doe",
        LocalDate.of(1995, 8, 10), "john.doe@example.com");

    /*
     Verifying the result.
     */
    assertNotNull(studentResponse);
    assertEquals("John Doe", studentResponse.getName());
    assertEquals("john.doe@example.com", studentResponse.getEmail());

    /*
     Verifying that the findStudentByEmail method and insertStudent method of the StudentMapper
      are called once each.
     */
    verify(studentMapper, times(1)).findStudentByEmail("john.doe@example.com");
    verify(studentMapper, times(1)).insertStudent(any());
  }

  /**
   * Testing the EmailAlreadyTaken exception in the addNewStudent method.
   */
  @Test
  void testAddNewStudentWithExistingEmail() {
    /*
     Mocking the findStudentByEmail method of the StudentMapper to return an existing
     student with the same email.
     */
    when(studentMapper.findStudentByEmail(any())).thenReturn
        (new Student("Existing Student",
            LocalDate.of(1990, 5, 15), "john.doe@example.com"));

    /*
     Calling the method to be tested and expecting an EmailAlreadyTakenException.
     */
    assertThrows(EmailAlreadyTakenException.class, () ->
        studentService.addNewStudent("John Doe",
            LocalDate.of(1995, 8, 10), "john.doe@example.com"));

    /*
     Verifying that the findStudentByEmail method of the StudentMapper is called once.
     */
    verify(studentMapper, times(1)).findStudentByEmail("john.doe@example.com");
  }

  /**
   * Testing the successful output of the deleteStudent method.
   */
  @Test
  void testDeleteStudentById_ExistingStudent() {
    /*
     Mocking the existsById method of the StudentMapper to return true (student exists).
     */
    when(studentMapper.existsById(anyLong())).thenReturn(true);

    /*
     Mocking the deleteStudentById method of the StudentMapper to simulate successful deletion.
     */
    doNothing().when(studentMapper).deleteStudentById(anyLong());

    /*
     Calling the method to be tested.
     */
    assertDoesNotThrow(() -> studentService.deleteStudentById(1L));

    /*
     Verifying that the existsById and deleteStudentById methods of the StudentMapper are
     called once each.
     */
    verify(studentMapper, times(1)).existsById(1L);
    verify(studentMapper, times(1)).deleteStudentById(1L);
  }

  /**
   * Testing the StudentNotFound exception in the deleteStudent method.
   */
  @Test
  void testDeleteStudentById_NonExistingStudent() {
    /*
    Mocking the existsById method of the StudentMapper to return false (student does not exist).
     */
    when(studentMapper.existsById(anyLong())).thenReturn(false);

    /*
     Calling the method to be tested and expecting a StudentNotFoundException.
     */
    assertThrows(StudentNotFoundException.class, () ->
        studentService.deleteStudentById(1L));

    /*
     Verifying that the existsById method of the StudentMapper is called once.
     */
    verify(studentMapper, times(1)).existsById(1L);
    /*
     Verifying that the deleteStudentById method of the StudentMapper is not called.
     */
    verify(studentMapper, never()).deleteStudentById(anyLong());
  }

  /**
   * Testing the successful output of the updateStudentById method.
   */
  @Test
  void testUpdateStudentById_ExistingStudent_UniqueEmail() {
    Long studentId = 1L;
    String name = "John Doe";
    String email = "john.doe@example.com";

    /*
     Mocking the findById method of the StudentMapper to return an existing student.
     */
    Student existingStudent = new Student(name,
        LocalDate.of(1995, 8, 10), email);
    when(studentMapper.findById(studentId)).thenReturn(existingStudent);

    /*
     Mocking the findStudentByEmail method of the StudentMapper to return null (email is unique).
     */
    when(studentMapper.findStudentByEmail(email)).thenReturn(null);

    /*
     Mocking the updateStudent method of the StudentMapper to simulate successful update.
     */
    doNothing().when(studentMapper).updateStudent(existingStudent);

    /*
     Calling the method to be tested.
     */
    assertDoesNotThrow(() -> studentService.updateStudentById
        (studentId, "Updated Name", "updated.email@example.com"));

    /*
     Verifying that the findById method of the StudentMapper is called once.
     */
    verify(studentMapper, times(1)).findById(studentId);
    /*
     Verifying that the findStudentByEmail method of the StudentMapper is called once with
     the updated email.
     */
    verify(studentMapper, times(1)).
        findStudentByEmail("updated.email@example.com");
    /*
     Verifying that the updateStudent method of the StudentMapper is called once.
     */
    verify(studentMapper, times(1)).updateStudent(existingStudent);

    /*
     Asserting that the student name and email are updated.
     */
    assertEquals("Updated Name", existingStudent.getName());
    assertEquals("updated.email@example.com", existingStudent.getEmail());
  }

  /**
   * Testing the EmailAlreadyTaken exception in the updateStudentById method.
   */
  @Test
  void testUpdateStudentById_ExistingStudent_DuplicateEmail() {
    Long studentId = 1L;
    String name = "John Doe";
    String email = "john.doe@example.com";

    /*
     Mocking the findById method of the StudentMapper to return an existing student.
     */
    Student existingStudent = new Student(name,
        LocalDate.of(1995, 8, 10), email);
    when(studentMapper.findById(studentId)).thenReturn(existingStudent);

    /*
     Mocking the findStudentByEmail method of the StudentMapper to return an existing
     student with the updated email.
     */
    when(studentMapper.findStudentByEmail("updated.email@example.com"))
        .thenReturn(
            new Student("Another Student", LocalDate.of
                (1998, 3, 15), "updated.email@example.com"));

    /*
     Calling the method to be tested and expecting an EmailAlreadyTakenException.
     */
    assertThrows(EmailAlreadyTakenException.class, () ->
        studentService.updateStudentById
            (studentId, "John Doe", "updated.email@example.com"));

    /*
     Verifying that the findById method of the StudentMapper is called once.
     */
    verify(studentMapper, times(1)).findById(studentId);
    /*
     Verifying that the findStudentByEmail method of the StudentMapper is called once with the
      updated email.
     */
    verify(studentMapper, times(1)).
        findStudentByEmail("updated.email@example.com");
    /*
     Verifying that the updateStudent method of the StudentMapper is not called.
     */
    verify(studentMapper, never()).updateStudent(any());

    /*
     Asserting that the student name and email are not updated.
     */
    assertEquals(name, existingStudent.getName());
    assertEquals(email, existingStudent.getEmail());
  }

  /**
   * Testing the StudentNotFoundException in the updateStudentById method.
   */
  @Test
  void testUpdateStudentById_NonExistingStudent() {
    Long nonExistingStudentId = 999L;
    String updatedName = "Updated Name";
    String updatedEmail = "updated.email@example.com";

    /*
     Mocking the findById method of the StudentMapper to return null (student does not exist).
     */
    when(studentMapper.findById(nonExistingStudentId)).thenReturn(null);

    /*
     Calling the method to be tested and expecting a StudentNotFoundException.
     */
    assertThrows(StudentNotFoundException.class, () ->
        studentService.updateStudentById(nonExistingStudentId, updatedName, updatedEmail));

    /*
     Verifying that the findById method of the StudentMapper is called once.
     */
    verify(studentMapper, times(1)).findById(nonExistingStudentId);
    /*
     Verifying that the findStudentByEmail method of the StudentMapper is not called.
     */
    verify(studentMapper, never()).findStudentByEmail(any());
    /*
     Verifying that the updateStudent method of the StudentMapper is not called.
     */
    verify(studentMapper, never()).updateStudent(any());
  }

  /**
   * Testing the successful output of the validateEmail method.
   */
  @ParameterizedTest
  @ValueSource(strings = {
      /*
      Valid email formats.
       */
      "john@example.com",
      "jane.doe@example.co.uk",
      "user12345@example.net"
  })
  void testValidateEmail_ValidFormat(String email) {
    assertDoesNotThrow(() -> StudentService.validateEmail(email));
  }

  /**
   * Testing empty emails in the validateEmail method.
   */
  @ParameterizedTest
  @ValueSource(strings = {
      "",
      "   "
  })
  void testValidateEmail_EmptyEmail(String email) {
    assertThrows(InvalidEmailFormatException.class, () -> StudentService.validateEmail(email));
  }

  /**
   * Testing invalid email formats in the validateEmail method.
   */
  @ParameterizedTest
  @ValueSource(strings = {
      "",
      "   ",
      "john@example",
      "jane.doe",
      "user12345@example.",
      "john@.com",
      "jane.doe@example",
      "@example.com",
      "john@.example.com"
  })
  void testValidateEmail_InvalidFormat(String email) {
    assertThrows(InvalidEmailFormatException.class, () -> StudentService.validateEmail(email));
  }

  /**
   * Test valid names in the validateName method.
   */
  @ParameterizedTest
  @ValueSource(strings = {
      "John",
      "Jane",
      "Alex Johnson Jr",
      "David",
      "A B"
  })
  void testValidateName_ValidNames(String name) {
    assertDoesNotThrow(() -> StudentService.validateName(name));
  }

  /**
   * Test invalid names in the validateName method.
   */
  @ParameterizedTest
  @ValueSource(strings = {
      "",
      "   ",
      "1234",
      "John123",
      "Name-",
      " name",
      "Name@",
      "LongNameLongNameLongNameLongNameLongNameLongNameLongNameLongNameLongNameLongName",
      "Name name name name name name name name name name name name name name name name"
  })
  void testValidateName_InvalidNames(String name) {
    assertThrows(InvalidNameException.class, () -> StudentService.validateName(name));
  }


  /**
   * Test valid date of birth output in the validateDOB method.
   */
  @Test
  void testValidateDOB_ValidDOB() {
    LocalDate validDOB = LocalDate.of(1990, 1, 1);
    assertDoesNotThrow(() -> StudentService.validateDOB(validDOB));
  }

  /**
   * Test future date of birth in the validateDOB method.
   */
  @Test
  void testValidateDOB_FutureDOB() {
    LocalDate futureDOB = LocalDate.now().plusDays(1);
    assertThrows(InvalidDOBException.class, () -> StudentService.validateDOB(futureDOB));
  }

  /**
   * Test date of birth older than 100 years in the validateDOB method.
   */
  @Test
  void testValidateDOB_OlderThan100YearsDOB() {
    LocalDate olderThan100YearsDOB = LocalDate.now().minusYears(101);
    assertThrows(InvalidDOBException.class, () -> StudentService.validateDOB(olderThan100YearsDOB));
  }
}


