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

    user.setPassword(encryptPassword(user, salt));

    userMapper.insertUser(user);
  }

  @Override
  public boolean isUsingId(String userId) {

    return userMapper.isUsingId(userId);
  }

  @Override
  public Optional<UserDto> findUserByIdAndPassword(UserDto user) {
    user.setPassword(encryptPassword(user, salt));
    return Optional.ofNullable(userMapper.findUserByIdAndPassword(user));
  }


  @Override
  public void loginUser(UserDto user, HttpSession session) {
    Optional<UserDto> result = findUserByIdAndPassword(user);

    if (!result.isPresent()) {
      throw new NoneExistentUserException("존재하지 않는 사용자입니다.");
    }

    session.setAttribute(LOGIN_ID, result.get().getId());
  }

  @Override
  public void logOutUser(UserDto user) {

  }

  /*
   * 암호화 메서드
   * @param user
   * @param salt
   * @return String
   */
  private String encryptPassword(UserDto user, String salt) {
    byte[] byteSalt = PasswordEncryptor.generateSalt(this.salt);
    return PasswordEncryptor.getEncrypt(user.getPassword(), byteSalt);
  }

  @Override
  public void updateUser(UserDto user) {
    userMapper.updateUser(user);
  }
}
