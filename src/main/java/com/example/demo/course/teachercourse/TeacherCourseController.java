package com.example.demo.course.teachercourse;

import com.example.demo.course.teachercourse.request.AddTeacherCourseRequest;
import com.example.demo.course.teachercourse.request.DeleteTeacherCourseRequest;
import com.example.demo.course.teachercourse.response.AddTeacherCourseResponse;
import com.example.demo.course.teachercourse.response.DeleteTeacherCourseResponse;
import com.example.demo.course.teachercourse.response.TeacherCourseResponses;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class is for handling the course teacher related HTTP requests.
 */
@RestController
@RequestMapping(path = "api/v1/teacher_course")
@AllArgsConstructor
public class TeacherCourseController {

  private final TeacherCourseService teacherCourseService;

  /**
   * Retrieves the list of available teacher course relations.
   *
   * @return the list of teacher course relations.
   */
  @GetMapping(path = "course/{courseId}")
  public ResponseEntity<TeacherCourseResponses> getTeacherCourseRelationsByCourseId(
      @PathVariable("courseId") Long courseId)
  {
    /*
    Get the list of teacher course relations from the service.
     */
    TeacherCourseResponses distributions =
        teacherCourseService.getTeacherCourseRelationsByCourseId(courseId);

    /*
    Generating the response.
     */
    return new ResponseEntity<>(distributions, HttpStatus.OK);
  }

  /**
   * Adds a new teacher course relation to the database.
   *
   * @param request the teacher course request body to be added
   * @return the added teacher course response
   */
  @PostMapping
  public ResponseEntity<AddTeacherCourseResponse> AddNewTeacherCourseRelation(@Valid
  @RequestBody AddTeacherCourseRequest request) {

    /*
    Preparing the response.
     */
    AddTeacherCourseResponse newRelation =
        teacherCourseService.addNewTeacherCourseRelation(
            request.getTeacherId(),
            request.getCourseId());

    /*
    Generating the response.
     */
    return ResponseEntity.ok(newRelation);
  }

  /**
   * Deletes a teacher course relation from the database.
   * @param request to delete a teacher course relation
   * @return response of the deleted teacher course relation
   */
  @DeleteMapping
  public ResponseEntity<DeleteTeacherCourseResponse> deleteTeacherCourseRelation(
      @RequestBody DeleteTeacherCourseRequest request){
    /*
    Preparing the response.
     */
    teacherCourseService.deleteTeacherCourseRelationByIds(request.getTeacherId(),request.getCourseId());
    DeleteTeacherCourseResponse response = new DeleteTeacherCourseResponse();
    response.setTeacherId(request.getTeacherId());
    response.setCourseId(request.getCourseId());
    /*
    Generating the response.
     */
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}