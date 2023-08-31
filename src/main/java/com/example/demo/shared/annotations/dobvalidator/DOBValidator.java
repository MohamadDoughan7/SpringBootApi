package com.example.demo.shared.annotations.dobvalidator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.LocalDate;

/**
 * This class is for validating the dates of birth across all the packages of the application.
 */
public class DOBValidator implements ConstraintValidator<ValidateDOB, LocalDate> {

  /**
   * This method is for the valid result.
   *
   * @param dob     object to validate
   * @param context context in which the constraint is evaluated
   * @return
   */
  @Override
  public boolean isValid(LocalDate dob, ConstraintValidatorContext context) {
    /*
    Checking if the value is null.
     */
    if (dob == null) {
      return true;
    }
    /*
    Setting the current date.
     */
    LocalDate currentDate = LocalDate.now();
    /*
    Ensuring that the date of birth is not greater than 100 years.
     */
    LocalDate maxDOB = currentDate.minusYears(100);
    /*
    Returning the response if it is not in the future or not greater than 100 years.
     */
    return !dob.isAfter(currentDate) && !dob.isBefore(maxDOB);
  }
}