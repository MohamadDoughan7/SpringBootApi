package com.example.demo.teacher;

import com.example.demo.teacher.exceptions.TeacherEmailAlreadyTakenException;
import com.example.demo.teacher.exceptions.TeacherNotFoundException;
import com.example.demo.teacher.response.AddTeacherResponse;
import com.example.demo.teacher.response.GetTeacherResponse;
import com.example.demo.teacher.response.TeacherResponses;
import java.time.LocalDate;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

/**
 * This class is for performing the business logic for the methods within the teacher package..
 */
@Service
@RequiredArgsConstructor
@Log4j2
public class TeacherService {

  private final ModelMapper modelMapper = new ModelMapper();
  private final TeacherRepository teacherRepository;

  /**
   * Retrieves the list of available teachers.
   *
   * @return list of available teachers.
   */
  public TeacherResponses getTeachers() {
    /*
    Log info about displaying the list of teachers.
     */
    log.info("List of existing teachers has been displayed.");
    /*
    Get the list of teachers from the service using the TeacherRepository interface,
    and then build the response.
     */
    return new TeacherResponses(
        teacherRepository.getAllTeachers().parallelStream()
            .map(teacherEntity -> modelMapper.map(teacherEntity, GetTeacherResponse.class))
            .collect(Collectors.toList())
    );
  }

  /**
   * Adds a new teacher and saves it in the database.
   *
   * @param name  the name of the teacher
   * @param dob   the date of birth of the teacher
   * @param email the email address of the teacher
   * @return the added teacher
   * @throws TeacherEmailAlreadyTakenException if the email is already taken
   */
  public AddTeacherResponse addTeacher(String name, LocalDate dob, String email, String subject) {
    /*
     Check if the email already exists in the database.
     */
    if (teacherRepository.findTeacherByEmail(email) != null) {
      String errorMessage = "Email already exists: " + email;
      throw new TeacherEmailAlreadyTakenException(errorMessage);
    }
    /*
     Create a new TeacherEntity object
     */
    TeacherEntity teacherEntity = new TeacherEntity();
    teacherEntity.setName(name);
    teacherEntity.setEmail(email);
    teacherEntity.setDob(dob);
    teacherEntity.setSubject(subject);
    /*
    Insert a new teacherEntity in database.
     */
    teacherRepository.insertTeacher(teacherEntity);
    /*
     Log info about the new teacherEntity added.
     */
    log.info("New teacherEntity added: {}", teacherEntity);
    /*
     Map the newly inserted teacherEntity to a AddTeacherResponse object and return it.
     */
    return modelMapper.map(teacherEntity, AddTeacherResponse.class);
  }

  /**
   * Deletes a teacher from the database.
   *
   * @param teacherId the ID of the teacher to be deleted
   * @throws TeacherNotFoundException if the teacher with the given ID does not exist
   */
  public void deleteTeacherById(Long teacherId) {
    /*
     Check if the teacher exists in the database based on the provided id.
     */
    if (!teacherRepository.existsById(teacherId)) {
      String errorMessage = "TeacherEntity with id " + teacherId + " does not exist.";
      throw new TeacherNotFoundException(errorMessage);
    }
    /*
     Delete the teacher from the database using the TeacherRepository interface.
     */
    teacherRepository.deleteTeacherById(teacherId);
    log.info("TeacherEntity with id " + teacherId + " has been deleted.");
  }

  /**
   * Updates the attributes of a teacher in the database.
   *
   * @param teacherId the ID of the teacher to be updated
   * @param name      the updated name
   * @param email     the updated email
   * @throws TeacherNotFoundException          if the teacher with the given ID does not exist
   * @throws TeacherEmailAlreadyTakenException if the email is already taken
   */
  public void updateTeacherById(Long teacherId,
      String name,
      String email,
      LocalDate dob,
      String subject) {
    /*
     Find the existing teacherEntity by ID using the TeacherRepository interface.
     */
    TeacherEntity teacherEntity = teacherRepository.findById(teacherId);
    if (teacherEntity == null) {
      String errorMessage = "TeacherEntity with id " + teacherId + " does not exist.";
      throw new TeacherNotFoundException(errorMessage);
    }
    /*
     Update the name and email of the teacherEntity.
     */
    teacherEntity.setName(name);
    teacherEntity.setEmail(email);
    teacherEntity.setDob(dob);
    teacherEntity.setSubject(subject);
    /*
     Update the teacherEntity in the database using the TeacherRepository interface.
     */
    teacherRepository.updateTeacher(teacherEntity);
    log.info("TeacherEntity with id " + teacherId + " has been updated.");
  }
}