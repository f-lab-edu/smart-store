package com.project.smartStore.security;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@PropertySource("classpath:salt.properties")
public class EncryptionConverter {

  @Value("${enc.salt}")
  public String salt;

  public String ConvertSHA256WithSalt(String inputString) {
    String inputWithSalt = inputString + salt;
    try {
      MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
      byte[] bytes = messageDigest.digest(inputWithSalt.getBytes(StandardCharsets.UTF_8));
      return bytesToHex(bytes);
    } catch (NoSuchAlgorithmException e) {
      throw new IllegalAlgorithmException("암호화 알고리즘을 찾는데 실패하였습니다.", e);
    }
  }

  private String bytesToHex(byte[] hash) {
    StringBuilder hexString = new StringBuilder(2 * hash.length);
    for (byte h : hash) {
      String hex = Integer.toHexString(0xff & h);
      if (hex.length() == 1) {
        hexString.append('0');
      }
      hexString.append(hex);
    }
    return hexString.toString();
  }

}
