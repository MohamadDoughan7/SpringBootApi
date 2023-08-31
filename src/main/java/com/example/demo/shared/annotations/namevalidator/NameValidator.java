package com.example.demo.shared.annotations.namevalidator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

/**
 * This class is for validating the names across all the packages of the application.
 */
public class NameValidator implements ConstraintValidator<ValidateName, String> {

  /*
  Defining the regex of the valid name.
   */
  private static final String NameRegex = "^[A-Za-z]+( [A-Za-z]+)*";

  /**
   * This method is for the valid result.
   *
   * @param value   object to validate
   * @param context context in which the constraint is evaluated
   * @return name validation
   */
  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    /*
    Checking if the value is null.
     */
    if (value == null) {
      return true;
    }
    /*
    Matching the result with defined regex.
     */
    Pattern pattern = Pattern.compile(NameRegex);
    /*
    Returning the matched result.
     */
    return pattern.matcher(value).matches();
  }
}