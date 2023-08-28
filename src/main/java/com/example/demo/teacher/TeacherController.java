package com.example.demo.teacher;

import com.example.demo.shared.Validation;
import com.example.demo.teacher.request.TeacherRequest;
import com.example.demo.teacher.response.TeacherResponse;
import com.example.demo.teacher.response.TeacherResponseList;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
 * This class is for handling the teacher-related HTTP requests.
 */
@RestController
@RequestMapping(path = "api/v1/teacher")
@AllArgsConstructor
public class TeacherController {

  private final TeacherService teacherService;

  /**
   * Retrieves the list of available teachers.
   *
   * @return the list of teachers
   */
  @GetMapping
  public ResponseEntity<TeacherResponseList> getTeachers() {

    /*
    Get the list of teachers from the service.
     */
    TeacherResponseList teachers = teacherService.getTeachers();

    /*
    Generating the response.
     */
    return new ResponseEntity<>(teachers, HttpStatus.OK);


  }

  /**
   * Adds a new teacher to the repository.
   *
   * @param request the teacher request body to be added
   * @return the added teacher response
   */
  @PostMapping
  public ResponseEntity<TeacherResponse> createNewTeacher(@Valid
  @RequestBody TeacherRequest request) {

    /*
    Validate the name, email and dob before adding a new teacher.
     */
    Validation.validateName(request.getName());
    Validation.validateEmail(request.getEmail());
    Validation.validateDOB(request.getDob());

    /*
    Preparing the response.
     */
    TeacherResponse newTeacher = teacherService.addTeacher(
        request.getName(),
        request.getDob(),
        request.getEmail(),
        request.getSubject());

    /*
    Generating the response.
     */
    return ResponseEntity.ok(newTeacher);
  }

  /**
   * Deletes a teacher from the repository.
   *
   * @param teacherId the ID of the teacher to be deleted
   * @return the deletion result
   */
  @DeleteMapping(path = "{teacherId}")
  public ResponseEntity<String> deleteTeacher(@PathVariable("teacherId") Long teacherId) {

    /*
    Preparing the response.
     */
    teacherService.deleteTeacherById(teacherId);


    /*
    Generating the response.
     */
    return ResponseEntity.ok("Teacher deleted successfully");
  }

  /**
   * Updates the attributes of a teacher.
   *
   * @param teacherId the ID of the teacher to be updated
   * @param name      the updated name
   * @param email     the updated email
   * @return the update result
   */
  @PutMapping("{teacherId}")
  public ResponseEntity<String> updateTeacher(
      @PathVariable("teacherId") Long teacherId,
      @RequestParam(required = false) String name,
      @RequestParam(required = false) String email) {


    /*
    Validating the name and email before updating a teacher.
     */
    Validation.validateName(name);
    Validation.validateEmail(email);


    /*
    Preparing the response.
     */
    teacherService.updateTeacherById(teacherId, name, email);

    /*
    Generating the response.
     */
    return ResponseEntity.ok("Teacher updated successfully");
  }
}





