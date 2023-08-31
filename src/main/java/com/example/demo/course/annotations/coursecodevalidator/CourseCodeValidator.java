package com.example.demo.course.annotations.coursecodevalidator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * This class is for validating the course code format.
 */
public class CourseCodeValidator implements ConstraintValidator<ValidateCourseCode, String> {

  /*
  Defining the regex for the curse code format.
   */
  private static final String CodeRegex = "^[A-Z]{3}\\d{3}$";

  /**
   * This method is for the valid format.
   *
   * @param value   object to validate
   * @param context context in which the constraint is evaluated
   * @return
   */
  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    /*
    Check if the value is null.
     */
    if (value == null) {
      return true;
    }
    /*
    Matching the format with the defining regex.
     */
    Pattern pattern = Pattern.compile(CodeRegex);
    /*
    Return the response.
     */
    return pattern.matcher(value).matches();
  }
}