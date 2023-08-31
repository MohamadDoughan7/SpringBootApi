package com.example.demo.shared.annotations.namevalidator;

import static org.junit.jupiter.api.Assertions.assertTrue;

import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * This class is for testing the NameValidator class.
 */
public class NameValidatorTest {

  private NameValidator nameValidator;

  @Mock
  private ConstraintValidatorContext context;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    nameValidator = new NameValidator();
  }

  @Test
  public void testValidName() {
    /*
     Valid name according to the regex
     */
    String validName = "John Doe";
    assertTrue(nameValidator.isValid(validName, context));
  }

}