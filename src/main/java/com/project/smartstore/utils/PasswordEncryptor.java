package com.project.smartstore.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.project.smartstore.exception.EncryptException;


public class PasswordEncryptor {

  /**
   * 비밀번호 암호화 메서드.
   */
  public static String getEncrypt(String text, byte[] salt) {
    String result = "";

    byte[] a = text.getBytes();
    byte[] bytes = new byte[a.length + salt.length];

    System.arraycopy(a, 0, bytes, 0, a.length);
    System.arraycopy(salt, 0, bytes, a.length, salt.length);

    try {
      MessageDigest md = MessageDigest.getInstance("SHA-256");
      md.update(bytes);

      byte[] byteData = md.digest();

      StringBuffer sb = new StringBuffer();
      for (int i = 0; i < byteData.length; i++) {
        sb.append(Integer.toString((byteData[i] & 0xFF) + 256, 16).substring(1));
      }

      result = sb.toString();
    } catch (NoSuchAlgorithmException e) {
      throw new EncryptException("암호화 작업 중 예외가 발생했습니다.");
    }

    return result;
  }


  /**
   * salt를 생성하는 메소드.
   */
  public static byte[] generateSalt(String salt) {

    return salt.getBytes();
  }
}
