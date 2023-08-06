package com.example.demo.student;

import com.example.demo.student.exceptions.EmailAlreadyTakenException;
import com.example.demo.student.exceptions.InvalidDOBException;
import com.example.demo.student.exceptions.InvalidEmailFormatException;
import com.example.demo.student.exceptions.InvalidNameException;
import com.example.demo.student.exceptions.StudentNotFoundException;
import com.example.demo.student.response.StudentResponse;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * This class is for performing the business logic for the methods.
 */
@Service
@RequiredArgsConstructor
public class StudentService {

  private static final Logger logger = LogManager.getLogger(StudentService.class);
  private final StudentMapper studentMap;

  /**
   * Retrieves the list of available students.
   *
   * @return list of available students.
   */
  @Transactional
  public List<StudentResponse> getStudents() {
    /*
    Log info about displaying the list of students.
     */
    logger.info("List of existing students has been displayed.");

    /*
    Get the list of students from the service using the StudentMap interface.
     */
    List<Student> students = studentMap.getAllStudents();

    /*
    Preparing the response.
     */
    List<StudentResponse> studentResponses;
    /*
    Converting the student entity to a response.
     */
    studentResponses = students.stream()
        .map(student -> {
          StudentResponse response = new StudentResponse();
          response.setId(student.getId());
          response.setName(student.getName());
          response.setDob(student.getDob());
          response.setEmail(student.getEmail());
          response.setAge(student.getAge());
          return response;
        })
        .collect(Collectors.toList());

    /*
    Generating the response.
     */
    return studentResponses;
  }


  /**
   * Adds a new student and saves it in the database.
   *
   * @param name  the name of the student
   * @param dob   the date of birth of the student
   * @param email the email address of the student
   * @return the added student
   * @throws EmailAlreadyTakenException if the email is already taken
   */

  @Transactional
  public StudentResponse addNewStudent(String name, LocalDate dob, String email) {

    /*
     Check if the email already exists in the database.
     */
    if (studentMap.findStudentByEmail(email) != null) {
      String errorMessage = "Email already exists: " + email;
      logger.error(errorMessage);
      throw new EmailAlreadyTakenException(errorMessage);
    }

    /*
     Create a new Student object and insert it into the database using the StudentMap interface.
     */
    Student student = new Student(name, dob, email);
    studentMap.insertStudent(student);

    /*
     Log info about the new student added.
     */
    logger.info("New student added: {}", student);

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
  @Transactional
  public void deleteStudentById(Long studentId) {
    /*
    Check if the student exists in the database based on the provided id.
     */
    if (!studentMap.existsById(studentId)) {
      logger.error("Student with id " + studentId + " does not exist.");
      throw new StudentNotFoundException("Student with id " + studentId + " does not exist.");
    }
    /*
     Delete the student from the database using the StudentMap interface.
     */
    studentMap.deleteStudentById(studentId);
    logger.info("Student with id " + studentId + " has been deleted.");
  }

  /**
   * Updates the attributes of a student in the database.
   *
   * @param studentId the ID of the student to be updated
   * @param name      the updated name
   * @param email     the updated email
   * @throws StudentNotFoundException   if the student with the given ID does not exist
   * @throws EmailAlreadyTakenException if the email is already taken
   */
  @Transactional
  public void updateStudentById(Long studentId, String name, String email) {
    validateName(name);
    validateEmail(email);

    /*
     Find the existing student by ID using the StudentMap interface.
     */
    Student student = studentMap.findById(studentId);
    if (student == null) {
      logger.error("Student with id " + studentId + " does not exist.");
      throw new StudentNotFoundException("Student with id " + studentId + " does not exist.");
    }

    /*
     Update the name and email of the student.
     */
    student.setName(name);
    if (!student.getEmail().equals(email)) {
      /*
       Check if the updated email is already taken by another student.
       */
      Student existingStudent = studentMap.findStudentByEmail(email);
      if (existingStudent != null && !Objects.equals(existingStudent.getId(), studentId)) {
        logger.error("Email " + email + " already taken.");
        throw new EmailAlreadyTakenException("Email " + email + " already taken");
      }
      student.setEmail(email);
    }

    /*
     Update the student in the database using the StudentMap interface.
     */
    studentMap.updateStudent(student);
    logger.info("Student with id " + studentId + " has been updated.");
  }

  /**
   * Validates the format of an email address.
   *
   * @param email the email address to validate
   * @throws InvalidEmailFormatException if the email format is invalid
   */
  public static void validateEmail(String email) {
    /*
    Ensuring the email is not empty or null.
     */
    if (email == null || email.isEmpty()) {
      logger.error("Invalid email format. Email should not be null.");
      throw new InvalidEmailFormatException("Invalid email format. Email should not be null");
    }
    /*
    Defining the regex of the email's format.
     */
    String emailRegex = "^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    /*
    Ensuring that the email is compatible with the defined format.
     */
    Pattern pattern = Pattern.compile(emailRegex);
    /*
    Incorrect input response.
     */
    if (!pattern.matcher(email).matches()) {
      logger.error("Invalid email format");
      throw new InvalidEmailFormatException("Invalid email format");
    }
  }

  /**
   * This method is for validating the name.
   *
   * @param name the name to validate
   */
  public static void validateName(String name) {
    /*
    Ensuring that the name field is not empty or null.
     */
    if (name == null || name.isEmpty()) {
      logger.error("Invalid name. Name should not be null.");
      throw new InvalidNameException("Invalid name. Name should not be null");
    }
    /*
    Defining the regex of the name format.
     */
    String nameRegex = "^[A-Z][a-zA-Z ]{0,28}[a-zA-Z]$";
    /*
    Ensuring that the name entered is compatible with the defined format.
     */
    Pattern pattern = Pattern.compile(nameRegex);
    /*
     Response for an invalid name input.
     */
    if (!pattern.matcher(name).matches()) {

      logger.error("Invalid name format");
      throw new InvalidNameException("Invalid name format");
    }
  }

  /**
   * This method is for validating the date of birth.
   *
   * @param dob the date of birth to validate
   */
  public static void validateDOB(LocalDate dob) {
    /*
     Ensure the DOB is not in the future.
     */
    LocalDate currentDate = LocalDate.now();

    if (dob.isAfter(currentDate)) {
      logger.error("Invalid DOB. Date of Birth cannot be in the future.");
      throw new InvalidDOBException("Invalid DOB. Date of Birth cannot be in the future.");
    }

    /*
     Ensure the DOB is not older than 100 years.
     */
    LocalDate maxDOB = currentDate.minusYears(100);
    if (dob.isBefore(maxDOB)) {
      logger.error("Invalid DOB. Date of Birth cannot be older than 100 years.");
      throw new InvalidDOBException("Invalid DOB. Date of Birth cannot be older than 100 years.");
    }
  }

}
