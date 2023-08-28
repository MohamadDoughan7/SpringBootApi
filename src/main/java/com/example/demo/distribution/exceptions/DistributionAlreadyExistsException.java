package com.example.demo.distribution.exceptions;

/**
 * This class represents the distribution already exists exception.
 */
public class DistributionAlreadyExistsException extends RuntimeException {

  /*
  Exception's constructor.
   */
  public DistributionAlreadyExistsException(String message) {
    super(message);
  }
}



