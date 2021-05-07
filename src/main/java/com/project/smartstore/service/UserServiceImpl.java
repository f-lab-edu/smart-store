package com.project.smartstore.service;

import com.project.smartstore.dto.UserDto;
import com.project.smartstore.exception.DuplicatedIdException;
import com.project.smartstore.exception.NoneExistentUserException;
import com.project.smartstore.mapper.UserMapper;
import com.project.smartstore.utils.PasswordEncryptor;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


/**
 * UserService 구현 클래스.
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserMapper userMapper;
  public static String LOGIN_ID = "loginId";

  /*
   * @Value는 properties의 프로퍼티를 읽을 수 있게 합니다.
   * properties파일은 ${}이며, xml파일은 #{}을 사용합니다.
   * 정적변수에는 injection이 불가하다는 점에 유의해야 합니다.
   */
  @Value("${encrypt.pw.salt}")
  private String salt;


  @Override
  public void joinUser(UserDto user) {

    if (isUsingId(user.getId())) {
      throw new DuplicatedIdException("동일한 아이디가 존재합니다. ");
    }
    encryptPassword(user);
    userMapper.insertUser(user);
  }

  @Override
  public boolean isUsingId(String userId) {

    return userMapper.isUsingId(userId);
  }

  @Override
  public Optional<UserDto> findUserByIdAndPassword(UserDto user) {
    encryptPassword(user);
    return Optional.ofNullable(userMapper.findUserByIdAndPassword(user));
  }

  @Override
  public void updateUser(UserDto user) {
    userMapper.updateUser(user);
  }
  
  @Override
  public void deleteUser(UserDto user, HttpSession session) {
    encryptPassword(user);
    userMapper.deleteUser(user);
    session.invalidate();
  }

  /*
   * 암호화 메서드
   * @param user
   */
  private void encryptPassword(UserDto user) {
    byte[] byteSalt = PasswordEncryptor.generateSalt(this.salt);
    user.setPassword(PasswordEncryptor.getEncrypt(user.getPassword(), byteSalt));
  }  
  
}
