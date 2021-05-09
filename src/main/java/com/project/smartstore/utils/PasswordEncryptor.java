package com.project.smartstore.utils;

import com.project.smartstore.exception.EncryptException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;



public class PasswordEncryptor {

  /**
   * 비밀번호 암호화 메서드.
   */
  public static String encryptPassword(String text, String salt) {
    String result = "";
    byte[] byteSalt = generateSalt(salt);

    byte[] a = text.getBytes();
    byte[] bytes = new byte[a.length + byteSalt.length];

    System.arraycopy(a, 0, bytes, 0, a.length);
    System.arraycopy(byteSalt, 0, bytes, a.length, byteSalt.length);

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
