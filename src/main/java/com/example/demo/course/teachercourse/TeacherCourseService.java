package com.example.demo.course.teachercourse;

import com.example.demo.course.teachercourse.exceptions.MaximumNumberOfCoursesReachedException;
import com.example.demo.course.teachercourse.exceptions.MaximumNumberOfTeachersReachedException;
import com.example.demo.course.teachercourse.exceptions.TeacherCourseRelationNotFoundException;
import com.example.demo.course.teachercourse.exceptions.TecaherCourseRelationAlreadyExistsException;
import com.example.demo.course.teachercourse.response.AddTeacherCourseResponse;
import com.example.demo.course.teachercourse.response.GetTeacherCourseResponse;
import com.example.demo.course.teachercourse.response.TeacherCourseResponses;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

/**
 * This class is for performing the business logic for the methods within the distribution package.
 */
@Service
@RequiredArgsConstructor
@Log4j2
public class TeacherCourseService {

  private final ModelMapper modelMapper = new ModelMapper();
  private final TeacherCourseRepository teacherCourseRepository;

  /**
   * Retrieves the list of available distributions.
   *
   * @return list of available distributions.
   */
  public TeacherCourseResponses getTeacherCourseRelationsByCourseId(long courseId) {
    /*
    Log info about displaying the list of distributions.
     */
    log.info("List of existing teacher course relations has been displayed.");
    /*
    Get the list of teacher course relations from the service using the TeacherCourseRepository
    interface, and then build the response.
     */
    return new TeacherCourseResponses(
        teacherCourseRepository.getTeacherCourseRelationsByCourseId(courseId)
            .parallelStream()
            .map(teacherCourseEntity -> modelMapper.map(teacherCourseEntity,
                GetTeacherCourseResponse.class))
            .collect(Collectors.toList())
    );
  }

  /**
   * Adds a new teacher course relation and saves it in the database.
   *
   * @param teacherId the id of the teacher that is assigned to teach the courses
   * @param courseId  the id of the course that the teacher is assigned to teach
   * @return the added teacher course relation.
   */
  public AddTeacherCourseResponse addNewTeacherCourseRelation(Long teacherId, Long courseId) {
    /*
     Check if the teacherCourseEntity already exists in the database based on teacherId and courseId
     */
    if (teacherCourseRepository.existsByIds(teacherId, courseId)) {
      String errorMessage =
          "Teacher Course relation already exists for teacherId: " + teacherId + " and courseId: "
              + courseId;
      throw new TecaherCourseRelationAlreadyExistsException(errorMessage);
    }
    /*
     Check if the teacher is already teaching 6 courses.
     */
    int currentTeacherCourseRelationsCount =
        teacherCourseRepository.getTeacherCourseRelationByTeacherId(teacherId);
    if (currentTeacherCourseRelationsCount >= 6) {
      /*
       You've reached the maximum number of courses for this teacher.
       */
      throw new MaximumNumberOfCoursesReachedException
          ("Maximum number of courses reached for teacher with ID " + teacherId);
    }
    /*
     Check if the course is taught by more than 3 teachers.
     */
    int currentCourseTeacherRelationsCount =
        teacherCourseRepository.getTeacherCourseCountByCourseId(courseId);
    if (currentCourseTeacherRelationsCount >= 3) {
      /*
       You've reached the maximum number of teachers for this course.
       */
      throw new MaximumNumberOfTeachersReachedException
          ("Maximum number of teachers limit reached for course with ID " + courseId);
    }
    /*
    Create a new TeacherCourseEntity object
    */
    TeacherCourseEntity teacherCourseEntity = new TeacherCourseEntity();
    teacherCourseEntity.setTeacherId(teacherId);
    teacherCourseEntity.setCourseId(courseId);
    /*
    Insert a new TeacherCourseEntity in database.
     */
    teacherCourseRepository.insertTeacherCourseRelation(teacherCourseEntity);
    /*
     Log info about the new teacherCourseEntity added.
     */
    log.info("New teacherCourseEntity added: {}", teacherCourseEntity);
    /*
     Map the newly inserted teacherCourseEntity to a AddTeacherCourseResponse object and return it.
     */
    return modelMapper.map(teacherCourseEntity, AddTeacherCourseResponse.class);
  }

  /**
   * Deletes a TeacherCourseEntity from the database.
   *
   * @param teacherId the ID of the teacher of the teacher course relation to be deleted
   * @param courseId  the ID of the course of the teacher courseEntity to be deleted
   * @throws TeacherCourseRelationNotFoundException if the TeacherCourseEntity with the given
    IDs does not exist
   */
  public void deleteTeacherCourseRelationByIds(Long teacherId, Long courseId) {
    /*
     Check if the teacher course relation exists in the database based on the provided ids.
     */
    if (!teacherCourseRepository.existsByIds(teacherId, courseId)) {
      String errorMessage = "TeacherCourseEntity with teacher id " + teacherId + " and course id "
          + courseId + " does not exist.";
      throw new TeacherCourseRelationNotFoundException(errorMessage);
    }
    /*
     Delete the teacher course relation from the database using the TeacherCourseRepository interface.
     */
    teacherCourseRepository.deleteTeacherCourseRelationByIds(teacherId, courseId);
    log.info("Teacher Course relation with teacher id " + teacherId + " and course id " +
        courseId + " has been deleted.");
  }
}