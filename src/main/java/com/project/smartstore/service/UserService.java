package com.project.smartstore.service;

import com.project.smartstore.dto.UserDto;
import java.util.Optional;
import javax.servlet.http.HttpSession;

/**
 * UserService : 구매자와 판매자가 공통으로 수행하는 기능을 추상화한 인터페이스.
 */
public interface UserService {

  public void joinUser(UserDto user);

  public boolean isUsingId(String userId);

  public Optional<UserDto> findUserByIdAndPassword(UserDto user);

  public void updateUser(UserDto user);
  
  public void deleteUser(UserDto user, HttpSession session);

}