package com.project.smartstore.exception;

public class NoneExistentUserException extends RuntimeException {
  
  public NoneExistentUserException(String message) {
    super(message);
  }
}
