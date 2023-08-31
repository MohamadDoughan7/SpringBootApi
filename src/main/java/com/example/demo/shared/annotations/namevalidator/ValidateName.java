package com.example.demo.shared.annotations.namevalidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation is for validating names throughout all the packages of teh application.
 */
@Documented
@Constraint(validatedBy = NameValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidateName {
  /*
  This is the message that will be displayed when the validation fails.
   */
  String message() default "Invalid name format";
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};
}

