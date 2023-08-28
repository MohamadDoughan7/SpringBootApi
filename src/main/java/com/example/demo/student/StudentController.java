package com.example.demo.student;

import com.example.demo.shared.Validation;
import com.example.demo.student.request.StudentRequest;
import com.example.demo.student.response.StudentResponse;
import com.example.demo.student.response.StudentResponseList;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class is for handling the student-related HTTP requests.
 */
@RestController
@RequestMapping(path = "api/v1/student")
@AllArgsConstructor
@EnableTransactionManagement
public class StudentController {

  private final StudentService studentService;

  /**
   * Retrieves the list of available students.
   *
   * @return the list of students
   */
  @GetMapping
  public ResponseEntity<StudentResponseList> getStudents() {

    /*
    Get the list of students from the service.
     */
    StudentResponseList students = studentService.getStudents();

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
  public ResponseEntity<StudentResponse> createNewStudent(@Valid
  @RequestBody StudentRequest request) {

    /*
    Validate the name, email and dob before adding a new student.
     */
    Validation.validateName(request.getName());
    Validation.validateEmail(request.getEmail());
    Validation.validateDOB(request.getDob());

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
    Validation.validateName(name);
    Validation.validateEmail(email);


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





