package com.example.demo.teacher;

import com.example.demo.teacher.request.AddTeacherRequest;
import com.example.demo.teacher.request.UpdateTeacherRequest;
import com.example.demo.teacher.response.AddTeacherResponse;
import com.example.demo.teacher.response.DeleteTeacherResponse;
import com.example.demo.teacher.response.TeacherResponses;
import com.example.demo.teacher.response.UpdateTeacherResponse;
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
  public ResponseEntity<TeacherResponses> getTeachers() {

    /*
    Get the list of teachers from the service.
     */
    TeacherResponses teachers = teacherService.getTeachers();

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
  public ResponseEntity<AddTeacherResponse> addNewTeacher(@Valid
  @RequestBody AddTeacherRequest request) {

    /*
    Preparing the response.
     */
    AddTeacherResponse newTeacher = teacherService.addTeacher(
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
   * @param id the ID of the teacher to be deleted
   * @return the deletion result
   */
  @DeleteMapping(path = "{id}")
  public ResponseEntity<DeleteTeacherResponse> deleteTeacher(@PathVariable("id") Long id) {

    /*
    Preparing the response.
     */
    teacherService.deleteTeacherById(id);
    DeleteTeacherResponse response = new DeleteTeacherResponse();
    response.setId(id);
    /*
    Generating the response.
     */
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  /**
   * Updates the attributes of a teacher.
   *
   * @param id      of the teacher to be updated
   * @param request of the changes to be done to a teacher
   * @return response of the updated teacher.
   */
  @PutMapping("{id}")
  public ResponseEntity<UpdateTeacherResponse> updateTeacher(
      @PathVariable("id") Long id,
      @RequestBody UpdateTeacherRequest request) {
    /*
    Preparing the response.
     */
    teacherService.updateTeacherById(
        id,
        request.getName(),
        request.getEmail(),
        request.getDob(),
        request.getSubject());
    UpdateTeacherResponse response = new UpdateTeacherResponse();
    response.setId(id);
    /*
    Generating the response.
     */
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}