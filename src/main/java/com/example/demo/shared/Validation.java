package com.example.demo.shared;

import com.example.demo.shared.exceptions.InvalidDOBException;
import com.example.demo.shared.exceptions.InvalidEmailFormatException;
import com.example.demo.shared.exceptions.InvalidNameException;
import java.time.LocalDate;
import java.util.regex.Pattern;

public class Validation {

  /**
   * Validates the format of an email address.
   *
   * @param email the email address to validate
   * @throws InvalidEmailFormatException if the email format is invalid
   */
  public static void validateEmail(String email) {

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
      String errorMessage = "Invalid email format";
      throw new InvalidEmailFormatException(errorMessage);
    }
  }

  /**
   * This method is for validating the name.
   *
   * @param name the name to validate
   */
  public static void validateName(String name) {
    /*
    Defining the regex of the name format.
     */
    String nameRegex = "^(?:[A-Z][a-zA-Z ]{0,28}[a-zA-Z](?:\\s+|$)){1,4}$";

    /*
    Ensuring that the name entered is compatible with the defined format.
     */
    Pattern pattern = Pattern.compile(nameRegex);
    /*
     Response for an invalid name input.
     */
    if (!pattern.matcher(name).matches()) {
      String errorMessage = "Invalid name format";
      throw new InvalidNameException(errorMessage);
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
      String errorMessage = "Invalid DOB. Date of Birth cannot be in the future.";
      throw new InvalidDOBException(errorMessage);
    }

    /*
     Ensure the DOB is not older than 100 years.
     */
    LocalDate maxDOB = currentDate.minusYears(100);
    if (dob.isBefore(maxDOB)) {
      String errorMessage = "Invalid DOB. Date of Birth cannot be older than 100 years.";
      throw new InvalidDOBException(errorMessage);
    }
  }
}
