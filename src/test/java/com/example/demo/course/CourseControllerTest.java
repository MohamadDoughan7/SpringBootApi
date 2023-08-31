package com.example.demo.course;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.example.demo.course.request.AddCourseRequest;
import com.example.demo.course.request.UpdateCourseRequest;
import com.example.demo.course.response.AddCourseResponse;
import com.example.demo.course.response.CourseResponses;
import com.example.demo.course.response.DeleteCourseResponse;
import com.example.demo.course.response.GetCourseResponse;
import com.example.demo.course.response.UpdateCourseResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * This class is for testing the methods of the CourseController class.
 */
public class CourseControllerTest {

  @InjectMocks
  private CourseController courseController;

  @Mock
  private CourseService courseService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  /**
   * This test is for the get course method.
   */
  @Test
  void testGetCourses() {
    /*
     Mock the course service to return a list of courses
     */
    List<GetCourseResponse> courses = new ArrayList<>();
    GetCourseResponse course1 = new GetCourseResponse();
    course1.setId(1L);
    course1.setName("Micro Economics");
    courses.add(course1);

    when(courseService.getCourses()).thenReturn(courses);
    /*
     Call the controller method
     */
    ResponseEntity<CourseResponses> responseEntity = courseController.getCourses();
    /*
     Verify the response
     */
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals(1, Objects.requireNonNull(responseEntity.getBody()).getCourses().size());
    assertEquals("Micro Economics", responseEntity.getBody().getCourses().get(0).getName());
  }

  /**
   * This test is for adding a new course method.
   */
  @Test
  void testAddNewCourse() {
    /*
     Arrange
     */
    AddCourseRequest addCourseRequest = new AddCourseRequest();
    addCourseRequest.setName("Micro Economics");
    addCourseRequest.setCode("ECO201");
    addCourseRequest.setField("Economics");
    addCourseRequest.setCapacity(30);

    AddCourseResponse addCourseResponse = new AddCourseResponse();
    addCourseResponse.setId(1L);
    addCourseResponse.setName(addCourseRequest.getName());
    addCourseResponse.setCode(addCourseRequest.getCode());
    addCourseResponse.setField(addCourseRequest.getField());
    addCourseResponse.setCapacity(addCourseRequest.getCapacity());

    when(courseService.addCourse(anyString(), anyString(), anyString(), anyInt()))
        .thenReturn(addCourseResponse);
    /*
     Act
     */
    ResponseEntity<AddCourseResponse> responseEntity = courseController.addNewCourse(
        addCourseRequest);
    /*
     Assert
     */
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals(addCourseResponse, responseEntity.getBody());
  }

  /**
   * This test is for the delete course method.
   */
  @Test
  void testDeleteCourse() {
    /*
     Call the controller method to delete a course
     */
    ResponseEntity<DeleteCourseResponse> responseEntity = courseController.deleteCourse(1L);

    /*
     Verify the response
     */
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals(1L, Objects.requireNonNull(responseEntity.getBody()).getId());
  }

  /**
   * This test is for update a course method.
   */
  @Test
  void testUpdateCourse() {
    // Call the controller method to update a course
    UpdateCourseRequest request = new UpdateCourseRequest();
    ResponseEntity<UpdateCourseResponse> responseEntity = courseController.updateCourse(1L,
        request);

    /*
     Verify the response
     */
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals(1L, Objects.requireNonNull(responseEntity.getBody()).getId());
  }
}