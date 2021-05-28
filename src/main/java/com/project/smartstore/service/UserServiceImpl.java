package com.project.smartstore.service;

import com.project.smartstore.dto.UserDto;
import com.project.smartstore.exception.DuplicatedIdException;
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
  private final LoginService sessionLoginService;

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
    UserDto encryptedUser = encryptUser(user);
    userMapper.insertUser(encryptedUser);
  }

  @Override
  public boolean isUsingId(String userId) {
    return userMapper.isUsingId(userId);
  }

  @Override
  public Optional<UserDto> findUserByIdAndPassword(UserDto user) {
    UserDto encryptedUser = encryptUser(user);
    return Optional.ofNullable(userMapper.findUserByIdAndPassword(encryptedUser));
  }

  @Override
  public void updateUser(UserDto user) {
    userMapper.updateUser(user);
  }

  @Override
  public void deleteUser(UserDto user) {
    UserDto encryptedUser = encryptUser(user);
    userMapper.deleteUser(encryptedUser);
    sessionLoginService.logout();
  }

  private UserDto encryptUser(UserDto user) {
    String encryptedPassword = PasswordEncryptor.encryptPassword(user.getPassword(), salt);

    UserDto encryptedUser = UserDto.builder()
        .id(user.getId())
        .password(encryptedPassword)
        .name(user.getName())
        .phone(user.getPhone())
        .type(user.getType())
        .address1(user.getAddress1())
        .address2(user.getAddress2())
        .build();

    return encryptedUser;
  }
}
