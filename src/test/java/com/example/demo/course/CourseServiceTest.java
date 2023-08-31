package com.example.demo.course;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

import com.example.demo.course.exceptions.CourseNameAlreadyTakenException;
import com.example.demo.course.exceptions.CourseNotFoundException;
import com.example.demo.course.request.AddCourseRequest;
import com.example.demo.course.response.AddCourseResponse;
import com.example.demo.course.response.GetCourseResponse;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * This class is for testing the methods of the CourseService class.
 */
public class CourseServiceTest {

  @InjectMocks
  private CourseService courseService;

  @Mock
  private CourseRepository courseRepository;


  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  /**
   * Testing the get courses method.
   */
  @Test
  void testGetCourses() {
    /*
     Mock the course repository
     */
    List<CourseEntity> courseEntities = new ArrayList<>();
    CourseEntity course1 = new CourseEntity();
    course1.setId(1L);
    course1.setName("Micro Economics");
    courseEntities.add(course1);

    when(courseRepository.getAllCourses()).thenReturn(courseEntities);

    /*
     Call the service method
     */
    List<GetCourseResponse> courses = courseService.getCourses();

    /*
     Verify the result
     */
    assertEquals(1, courses.size());
    assertEquals("Micro Economics", courses.get(0).getName());
  }

  /**
   * Testing the add new course method.
   */
  @Test
  public void testAddNewCourse() {
    /*
     Arrange
     */
    AddCourseRequest request = new AddCourseRequest();
    request.setName("Micro Economics");
    request.setCode("ECO201");
    request.setField("Economics");
    request.setCapacity(30);

    when(courseRepository.findCourseByName("Micro Economics")).thenReturn(null);

    /*
     Use doAnswer to perform an action when insertStudent is called
     */
    doAnswer(invocation -> {
      CourseEntity entity = invocation.getArgument(0);
      entity.setId(1L); // Set an ID for the entity, as if it was added to the database
      return null; // Return null since insertStudent returns void
    }).when(courseRepository).insertCourse(any(CourseEntity.class));

    /*
     Act
     */
    AddCourseResponse addCourseResponse = courseService.addCourse(
        request.getName(), request.getCode(), request.getField(), request.getCapacity());

    /*
     Assert
     */
    assertEquals(1L, addCourseResponse.getId());
    assertEquals(request.getName(), addCourseResponse.getName());
    assertEquals(request.getCode(), addCourseResponse.getCode());
    assertEquals(request.getField(), addCourseResponse.getField());
    assertEquals(request.getCapacity(), addCourseResponse.getCapacity());
  }

  /**
   * Testing the course name already taken exception in the add course method.
   */
  @Test
  void testAddCourseWithNameAlreadyTaken() {
    /*
     Mock the course repository to return a course with the same name
     */
    CourseEntity existingCourse = new CourseEntity();
    existingCourse.setId(1L);
    existingCourse.setName("Micro Economics");

    when(courseRepository.findCourseByName("Micro Economics")).thenReturn(existingCourse);

    /*
     Call the service method and expect an exception
     */
    assertThrows(CourseNameAlreadyTakenException.class, () -> courseService.addCourse(
        "Micro Economics", "ECO201", "Economics", 30));
  }

  /**
   * Testing the delete course by id method.
   */
  @Test
  void testDeleteCourseById() {
    /*
     Mock the course repository to return a course with the specified ID
     */
    when(courseRepository.existsById(1L)).thenReturn(true);

    /*
     Call the service method
     */
    assertDoesNotThrow(() -> courseService.deleteCourseById(1L));
  }

  /**
   * Testing the courseId not found exception in the delete course method.
   */
  @Test
  void testDeleteCourseByIdNotFound() {
    /*
     Mock the course repository to return that the course with the specified ID does not exist
     */
    when(courseRepository.existsById(1L)).thenReturn(false);

    /*
     Call the service method and expect an exception
     */
    assertThrows(CourseNotFoundException.class, () -> courseService.deleteCourseById(1L));
  }

  /**
   * Testing the update course by id method.
   */
  @Test
  void testUpdateCourseById() {
    // Mock the course repository to return an existing course
    CourseEntity existingCourse = new CourseEntity();
    existingCourse.setId(1L);
    existingCourse.setName("Micro Economics");
    existingCourse.setCode("ECO201");
    existingCourse.setField("Economics");
    existingCourse.setCapacity(30);

    when(courseRepository.findById(1L)).thenReturn(existingCourse);

    /*
     Call the service method to update the course
     */
    assertDoesNotThrow(() -> courseService.updateCourseById(
        1L, "Updated Course", "UCS101", "Updated Field", 40));

    /*
     Verify that the course properties have been updated
     */
    assertEquals("Updated Course", existingCourse.getName());
    assertEquals("UCS101", existingCourse.getCode());
    assertEquals("Updated Field", existingCourse.getField());
    assertEquals(40, existingCourse.getCapacity());
  }

  @Test
  void testUpdateCourseByIdNotFound() {
    /*
     Mock the course repository to return that the course with the specified ID does not exist
     */
    when(courseRepository.findById(1L)).thenReturn(null);

    /*
     Call the service method and expect an exception
     */
    assertThrows(CourseNotFoundException.class, () -> courseService.updateCourseById(
        1L, "Updated Course", "UCS101", "Updated Field", 40));
  }
}