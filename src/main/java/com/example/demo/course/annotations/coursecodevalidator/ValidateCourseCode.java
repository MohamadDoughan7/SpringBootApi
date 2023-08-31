package com.example.demo.course.annotations.coursecodevalidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation is for validating the course code.
 */
@Documented
@Constraint(validatedBy = CourseCodeValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidateCourseCode {

  /*
  The message that will be displayed when validation fails.
   */
  String message() default "Invalid course code format";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}

