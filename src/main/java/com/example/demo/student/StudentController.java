package com.example.demo.student;

import com.example.demo.student.request.AddStudentRequest;
import com.example.demo.student.request.UpdateStudentRequest;
import com.example.demo.student.response.AddStudentResponse;
import com.example.demo.student.response.DeleteStudentResponse;
import com.example.demo.student.response.StudentResponses;
import com.example.demo.student.response.UpdateStudentResponse;
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
  public ResponseEntity<StudentResponses> getStudents() {

    /*
    Get the list of students from the service.
     */
    StudentResponses students = studentService.getStudents();

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
  public ResponseEntity<AddStudentResponse> addNewStudent(@Valid
  @RequestBody AddStudentRequest request) {
    /*
    Preparing the response.
     */
    AddStudentResponse newStudent = studentService.addNewStudent(
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
   * @param id the ID of the student to be deleted
   * @return the deletion result
   */
  @DeleteMapping(path = "{id}")
  public ResponseEntity<DeleteStudentResponse> deleteStudent (@PathVariable("id") Long id) {
    /*
    Preparing the response.
     */
    studentService.deleteStudentById(id);
    DeleteStudentResponse response = new DeleteStudentResponse();
    response.setId(id);
    /*
    Generating the response.
     */
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  /**
   * Updates the attributes of a student.
   * @param id of the student to be updated
   * @param request of the updates to be done to a course
   * @return response with the updated student
   */
  @PutMapping("{id}")
  public ResponseEntity<UpdateStudentResponse> updateStudent(
      @PathVariable("id") Long id,
      @RequestBody UpdateStudentRequest request){
    /*
    Preparing the response.
     */
    studentService.updateStudentById(
        id,
        request.getName(),
        request.getEmail(),
        request.getDob());
    UpdateStudentResponse response = new UpdateStudentResponse();
    response.setId(id);
    /*
    Generating the response.
     */
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}