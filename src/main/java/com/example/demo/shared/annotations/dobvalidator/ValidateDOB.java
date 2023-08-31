package com.example.demo.shared.annotations.dobvalidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation is for validating dates of birth throughout all the packages of the application.
 */
@Documented
@Constraint(validatedBy = DOBValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidateDOB {

  /*
  This is the message that will be displayed when the validation fails.
   */
  String message() default "Invalid date of birth";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}