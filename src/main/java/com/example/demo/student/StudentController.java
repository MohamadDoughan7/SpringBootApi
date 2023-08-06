package com.example.demo.student;

import com.example.demo.student.request.StudentRequest;
import com.example.demo.student.response.StudentResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This class is for handling the student-related HTTP requests.
 */
@RestController
@RequestMapping(path = "api/v1/student")
@AllArgsConstructor
public class StudentController {

  private final StudentService studentService;

  /**
   * Retrieves the list of available students.
   *
   * @return the list of students
   */
  @GetMapping
  public ResponseEntity<List<StudentResponse>> getStudents() {
    /*
      Get the list of students from the service.
     */
    List<StudentResponse> students = studentService.getStudents();
    /*
      Generating the response.
     */
    return new ResponseEntity<>(students, HttpStatus.OK);
  }

  /**
   * Adds a new student to the repository.
   *
   * @param request the student request body to be added
   * @return the added student response
   */
  @PostMapping
  public ResponseEntity<StudentResponse> createNewStudent(@RequestBody StudentRequest request) {
    /*
      Validating the name, email, and dob before adding a new student.
     */
    StudentService.validateName(request.getName());
    StudentService.validateEmail(request.getEmail());
    StudentService.validateDOB(request.getDob());

    /*
      Preparing the response.
     */
    StudentResponse newStudent = studentService.addNewStudent(
        request.getName(),
        request.getDob(),
        request.getEmail());
    /*
     Generating the response.
     */
    return ResponseEntity.ok(newStudent);
  }

  /**
   * Deletes a student from the repository.
   *
   * @param studentId the ID of the student to be deleted
   * @return the deletion result
   */
  @DeleteMapping(path = "{studentId}")
  public ResponseEntity<String> deleteStudent(@PathVariable("studentId") Long studentId) {
    /*
      Preparing the response.
     */
    studentService.deleteStudentById(studentId);
    /*
      Generating the response.
     */
    return ResponseEntity.ok("Student deleted successfully");
  }

  /**
   * Updates the attributes of a student.
   *
   * @param studentId the ID of the student to be updated
   * @param name      the updated name
   * @param email     the updated email
   * @return the update result
   */
  @PutMapping("{studentId}")
  public ResponseEntity<String> updateStudent(
      @PathVariable("studentId") Long studentId,
      @RequestParam(required = false) String name,
      @RequestParam(required = false) String email) {

    /*
     Validating the name and email before updating a student.
     */
    StudentService.validateName(name);
    StudentService.validateEmail(email);

    /*
      Preparing the response.
     */
    studentService.updateStudentById(studentId, name, email);
    /*
      Generating the response.
     */
    return ResponseEntity.ok("Student updated successfully");
  }
}
