package com.example.demo.shared.annotations.dobvalidator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import jakarta.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

/**
 * This class is for testing the DOBValidator class.
 */
class DOBValidatorTest {

  private DOBValidator dobValidator;

  @Mock
  private ConstraintValidatorContext context;

  @BeforeEach
  void setUp() {
    dobValidator = new DOBValidator();
    context = prepareMockContext();
  }

  private ConstraintValidatorContext prepareMockContext() {
    return null;
  }

  /**
   * Testing the validation method.
   */
  @Test
  void testValidDOB() {
    /*
     A valid date of birth within the last 100 years
     */
    LocalDate validDOB = LocalDate.of(1990, 1, 1);
    assertTrue(dobValidator.isValid(validDOB, context));
  }

  /**
   * Testing the future dob validation.
   */
  @Test
  void testFutureDOB() {
    /*
     A date of birth in the future
     */
    LocalDate futureDOB = LocalDate.now().plusDays(1);
    assertFalse(dobValidator.isValid(futureDOB, context));
  }

  /**
   * Testing the validation of a past dob.
   */
  @Test
  void testInvalidPastDOB() {
    /*
     A date of birth more than 100 years ago
     */
    LocalDate invalidPastDOB = LocalDate.of(1850, 1, 1);
    assertFalse(dobValidator.isValid(invalidPastDOB, context));
  }

  /**
   * Testing null dob.
   */
  @Test
  void testNullDOB() {
    /*
     Null date of birth should be considered valid
     */
    assertTrue(dobValidator.isValid(null, context));
  }
}