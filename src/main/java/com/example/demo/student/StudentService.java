package com.example.demo.student;

import static com.example.demo.shared.Validation.validateEmail;
import static com.example.demo.shared.Validation.validateName;

import com.example.demo.student.exceptions.StudentEmailAlreadyTakenException;
import com.example.demo.student.exceptions.StudentNotFoundException;
import com.example.demo.student.response.StudentResponse;
import com.example.demo.student.response.StudentResponseList;
import java.time.LocalDate;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * This class is for performing the business logic for the methods within the student package.
 */
@Service
@RequiredArgsConstructor
@Log4j2
public class StudentService {


  private final StudentMapper studentMapper;

  /**
   * Retrieves the list of available students.
   *
   * @return list of available students.
   */
  public StudentResponseList getStudents() {
    /*
    Log info about displaying the list of students.
     */
    log.info("List of existing students has been displayed.");

    /*
    Get the list of students from the service using the StudentMap interface,
    and then build the response.
     */
    return new StudentResponseList(
        studentMapper.getAllStudents().stream()
            .map(student -> new StudentResponse(
                student.getId(),
                student.getName(),
                student.getDob(),
                student.getEmail(),
                student.getAge()))
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
  @Transactional(rollbackFor = {Exception.class})
  public StudentResponse addNewStudent(String name, LocalDate dob, String email) {

    /*
     Check if the email already exists in the database.
     */
    if (studentMapper.findStudentByEmail(email) != null) {
      String errorMessage = "Email already exists: " + email;
      throw new StudentEmailAlreadyTakenException(errorMessage);
    }

    /*
     Create a new Student object and insert it into the database using the StudentMap interface.
     */
    Student student = new Student(name, dob, email);
    studentMapper.insertStudent(student);

    /*
     Log info about the new student added.
     */
    log.info("New student added: {}", student);

    /*
     Map the newly inserted student to a StudentResponse object and return it.
     */
    return new StudentResponse(student.getId(), student.getName(), student.getDob(),
        student.getEmail(), student.getAge());
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
    if (!studentMapper.existsById(studentId)) {
      String errorMessage = "Student with id " + studentId + " does not exist.";
      throw new StudentNotFoundException(errorMessage);
    }
    /*
     Delete the student from the database using the StudentMap interface.
     */
    studentMapper.deleteStudentById(studentId);
    log.info("Student with id " + studentId + " has been deleted.");
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

  public void updateStudentById(Long studentId, String name, String email) {
    validateName(name);
    validateEmail(email);
    /*
     Find the existing student by ID using the StudentMap interface.
     */
    Student student = studentMapper.findById(studentId);
    if (student == null) {
      String errorMessage = "Student with id " + studentId + " does not exist.";
      throw new StudentNotFoundException(errorMessage);
    }

    /*
     Update the name and email of the student.
     */
    student.setName(name);
    student.setEmail(email);

    /*
     Update the student in the database using the StudentMap interface.
     */
    studentMapper.updateStudent(student);
    log.info("Student with id " + studentId + " has been updated.");
  }
}