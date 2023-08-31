package com.example.demo.course.annotations.coursenamevalidator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * This class is for validating the course name.
 */
public class CourseNameValidator implements ConstraintValidator<ValidateCourseName, String> {

  /*
  Defining the regex of the correct course name format.
   */
  private static final String CourseNameRegex = "^(?!.* {2,})[A-Za-z0-9 ]{1,100}$";

  /**
   * This method is for the valid result.
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
    Matching the result with the defined regex.
     */
    Pattern pattern = Pattern.compile(CourseNameRegex);
    /*
    Returning the response.
     */
    return pattern.matcher(value).matches();
  }
}
