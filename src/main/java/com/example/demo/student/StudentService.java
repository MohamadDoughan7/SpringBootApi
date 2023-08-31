package com.example.demo.student;

import com.example.demo.student.exceptions.StudentEmailAlreadyTakenException;
import com.example.demo.student.exceptions.StudentNotFoundException;
import com.example.demo.student.response.AddStudentResponse;
import com.example.demo.student.response.GetStudentResponse;
import com.example.demo.student.response.StudentResponses;
import java.time.LocalDate;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

/**
 * This class is for performing the business logic for the methods within the student package.
 */
@Service
@RequiredArgsConstructor
@Log4j2
public class StudentService {

  private static final ModelMapper modelMapper = new ModelMapper();
  private final StudentRepository studentRepository;

  /**
   * Retrieves the list of available students.
   *
   * @return list of available students.
   */
  public StudentResponses getStudents() {
    /*
    Log info about displaying the list of students.
     */
    log.info("List of existing students has been displayed.");
    /*
    Get the list of students from the service using the StudentRepository interface,
    and then build the response.
     */
    return new StudentResponses(
        studentRepository.getAllStudents().parallelStream()
            .map(studentEntity -> modelMapper.map(studentEntity, GetStudentResponse.class))
            .collect(Collectors.toList())
    );
  }

  /**
   * Adds a new student and saves it in the database.
   *
   * @param name  the name of the student
   * @param dob   the date of birth of the student
   * @param email the email address of the student
   * @return the added student
   * @throws StudentEmailAlreadyTakenException if the email is already taken
   */
  public AddStudentResponse addNewStudent(
      String name,
      LocalDate dob,
      String email) {
    /*
    Check if the email already exists in the database.
    */
    if (studentRepository.findStudentByEmail(email) != null) {
      String errorMessage = "Email already exists: " + email;
      throw new StudentEmailAlreadyTakenException(errorMessage);
    }
    /*
    Create a new StudentEntity object
    */
    StudentEntity studentEntity = new StudentEntity();
    studentEntity.setName(name);
    studentEntity.setEmail(email);
    studentEntity.setDob(dob);
    /*
    Insert a new studentEntity in database.
     */
    studentRepository.insertStudent(studentEntity);
    /*
    Log info about the new studentEntity added.
    */
    log.info("New studentEntity added: {}", studentEntity);
    /*
     Map the newly inserted studentEntity to a AddStudentResponse object and return it.
     */
    return modelMapper.map(studentEntity, AddStudentResponse.class);
  }

  /**
   * Deletes a student from the database.
   *
   * @param studentId the ID of the student to be deleted
   * @throws StudentNotFoundException if the student with the given ID does not exist
   */
  public void deleteStudentById(Long studentId) {
    /*
     Check if the student exists in the database based on the provided id.
     */
    if (!studentRepository.existsById(studentId)) {
      String errorMessage = "StudentEntity with id " + studentId + " does not exist.";
      throw new StudentNotFoundException(errorMessage);
    }
    /*
     Delete the student from the database using the StudentRepository interface.
     */
    studentRepository.deleteStudentById(studentId);
    log.info("StudentEntity with id " + studentId + " has been deleted.");
  }

  /**
   * Updates the attributes of a student in the database.
   *
   * @param studentId the ID of the student to be updated
   * @param name      the updated name
   * @param email     the updated email
   * @throws StudentNotFoundException          if the student with the given ID does not exist
   * @throws StudentEmailAlreadyTakenException if the email is already taken
   */

  public void updateStudentById(Long studentId, String name, String email, LocalDate dob) {
    /*
     Find the existing studentEntity by ID using the StudentRepository interface.
     */
    StudentEntity studentEntity = studentRepository.findById(studentId);
    if (studentEntity == null) {
      String errorMessage = "StudentEntity with id " + studentId + " does not exist.";
      throw new StudentNotFoundException(errorMessage);
    }

    /*
     Update the name, email, and dob of the studentEntity.
     */
    studentEntity.setName(name);
    studentEntity.setEmail(email);
    studentEntity.setDob(dob);

    /*
     Update the studentEntity in the database using the StudentRepository interface.
     */
    studentRepository.updateStudent(studentEntity);
    log.info("StudentEntity with id " + studentId + " has been updated.");
  }
}