package com.project.smartstore.exception;

public class ExceedMaximumCountException extends RuntimeException {

  public ExceedMaximumCountException(String message) {
    super(message);
  }

  public ExceedMaximumCountException() {}
}
