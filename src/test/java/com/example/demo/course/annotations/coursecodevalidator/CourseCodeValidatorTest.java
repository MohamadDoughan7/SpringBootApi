package com.example.demo.course.annotations.coursecodevalidator;


import static org.junit.jupiter.api.Assertions.assertTrue;

import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * This class is for testing the methods of the CourseCodeValidator class.
 */
public class CourseCodeValidatorTest {

  private CourseCodeValidator codeValidator;

  @Mock
  private ConstraintValidatorContext context;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    codeValidator = new CourseCodeValidator();
  }

  /**
   * This test is for the valid course code.
   */
  @Test
  public void testValidCode() {
    /*
     Valid name according to the regex
     */
    String validCode = "ECO201";
    assertTrue(codeValidator.isValid(validCode, context));
  }

}
