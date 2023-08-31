package com.example.demo.course.annotations.coursenamevalidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This class is for validating the course name.
 */
@Documented
@Constraint(validatedBy = CourseNameValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidateCourseName {

  /*
  This is the message that will be displayed when the validation fails.
   */
  String message() default "Invalid course name format";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}