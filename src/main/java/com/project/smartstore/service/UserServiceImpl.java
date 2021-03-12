package com.project.smartstore.service;

import com.project.smartstore.dto.UserDto;
import com.project.smartstore.exception.DuplicatedIdException;
import com.project.smartstore.mapper.UserMapper;
import com.project.smartstore.utils.PasswordEncryptor;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserMapper userMapper;

  @Override
  public void joinUser(UserDto user) {
    if (isUsingId(user.getId())) {
      throw new DuplicatedIdException("동일한 아이디가 존재합니다. ");
    }

    String salt = PasswordEncryptor.generateSalt();
    String encryptedPassword = PasswordEncryptor.getEncrypt(user.getPassword(), salt);
    user.setPassword(encryptedPassword);

    userMapper.insertUser(user);
  }

  @Override
  public boolean isUsingId(String userId) {
    return userMapper.isUsingId(userId);
  }

  @Override
  public Optional<UserDto> findUserByIdAndPassword(UserDto user) {
    Optional<UserDto> result = Optional.ofNullable(userMapper.findUserById(user));
    return userMapper.findUserByIdAndPassword(result.orElse(null));
  }


  @Override
  public boolean loginUser(UserDto user, HttpSession session) {
    Optional<UserDto> result = findUserByIdAndPassword(user);

    if (result.isPresent()) {
      session.setAttribute("loginId", result.get().getId());
    }

    return result.isPresent();
  }

  @Override
  public void logOutUser(UserDto user) {

  }
}
