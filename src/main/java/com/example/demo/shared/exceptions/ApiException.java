package com.example.demo.shared.exceptions;

import java.time.ZonedDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * * This class represents the object that should be returned =by the exceptions
 */
@AllArgsConstructor
@Getter
public class ApiException {

  /*
  Message field.
   */
  private final String message;

  /*
  http status field.
   */
  private final String status;

  /*
  Time stamp field.
   */
  private final ZonedDateTime timeStamp;


}
