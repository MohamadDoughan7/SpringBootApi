package com.example.demo.teacher;

import static com.example.demo.shared.Validation.validateEmail;
import static com.example.demo.shared.Validation.validateName;

import com.example.demo.teacher.exceptions.TeacherEmailAlreadyTakenException;
import com.example.demo.teacher.exceptions.TeacherNotFoundException;
import com.example.demo.teacher.response.TeacherResponse;
import com.example.demo.teacher.response.TeacherResponseList;
import java.time.LocalDate;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

/**
 * This class is for performing the business logic for the methods within the teacher package..
 */
@Service
@RequiredArgsConstructor
@Log4j2
public class TeacherService {


  private final TeacherMapper teacherMapper;

  /**
   * Retrieves the list of available teachers.
   *
   * @return list of available teachers.
   */
  public TeacherResponseList getTeachers() {
    /*
    Log info about displaying the list of teachers.
     */
    log.info("List of existing teachers has been displayed.");

    /*
    Get the list of teachers from the service using the TeacherMapper interface,
    and then build the response.
     */
    return new TeacherResponseList(
        teacherMapper.getAllTeachers().stream()
            .map(teacher -> new TeacherResponse(
                teacher.getId(),
                teacher.getName(),
                teacher.getDob(),
                teacher.getEmail(),
                teacher.getSubject()))
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

  public TeacherResponse addTeacher(String name, LocalDate dob, String email, String subject) {

    /*
     Check if the email already exists in the database.
     */
    if (teacherMapper.findTeacherByEmail(email) != null) {
      String errorMessage = "Email already exists: " + email;
      throw new TeacherEmailAlreadyTakenException(errorMessage);
    }

    /*
     Create a new Teacher object and insert it into the database using the TeacherMapper interface.
     */
    Teacher teacher = new Teacher(name, dob, email, subject);
    teacherMapper.insertTeacher(teacher);

    /*
     Log info about the new teacher added.
     */
    log.info("New teacher added: {}", teacher);

    /*
     Map the newly inserted teacher to a TeacherResponse object and return it.
     */
    return new TeacherResponse(teacher.getId(), teacher.getName(), teacher.getDob(),
        teacher.getEmail(), teacher.getSubject());
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
    if (!teacherMapper.existsById(teacherId)) {
      String errorMessage = "Teacher with id " + teacherId + " does not exist.";
      throw new TeacherNotFoundException(errorMessage);
    }
    /*
     Delete the student from the database using the StudentMap interface.
     */
    teacherMapper.deleteTeacherById(teacherId);
    log.info("Teacher with id " + teacherId + " has been deleted.");
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
  public void updateTeacherById(Long teacherId, String name, String email) {
    validateName(name);
    validateEmail(email);

    /*
     Find the existing teacher by ID using the TeacherMapper interface.
     */
    Teacher teacher = teacherMapper.findById(teacherId);
    if (teacher == null) {
      String errorMessage = "Teacher with id " + teacherId + " does not exist.";
      throw new TeacherNotFoundException(errorMessage);
    }

    /*
     Update the name and email of the teacher.
     */
    teacher.setName(name);
    teacher.setEmail(email);


    /*
     Update the teacher in the database using the TeacherMapper interface.
     */
    teacherMapper.updateTeacher(teacher);
    log.info("Teacher with id " + teacherId + " has been updated.");
  }

}
