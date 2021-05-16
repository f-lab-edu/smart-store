package com.project.smartStore.security;

/*
  MessageDigest.getInstance(argument);에서 알고리즘을 못찾을 경우
*/
public class IllegalAlgorithmException extends RuntimeException {

  IllegalAlgorithmException(String message, Throwable cause){
    super(message, cause);
  }

}
