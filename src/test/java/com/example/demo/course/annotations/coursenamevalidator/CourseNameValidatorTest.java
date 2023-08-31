package com.example.demo.course.annotations.coursenamevalidator;

import static org.junit.jupiter.api.Assertions.assertTrue;

import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * This class is for testing the methods of the CourseNameValidator class.
 */
public class CourseNameValidatorTest {

  private CourseNameValidator nameValidator;

  @Mock
  private ConstraintValidatorContext context;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    nameValidator = new CourseNameValidator();
  }

  /**
   * This test is for the validation  of the course name.
   */
  @Test
  public void testValidName() {
    /*
     Valid name according to the regex.
     */
    String validName = "Micro Economics";
    assertTrue(nameValidator.isValid(validName, context));
  }
}