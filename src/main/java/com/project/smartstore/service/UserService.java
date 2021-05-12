package com.project.smartstore.service;

import com.project.smartstore.dto.UserDto;
import java.util.Optional;
import javax.servlet.http.HttpSession;

public interface UserService {

  public void joinUser(UserDto user);

  public boolean isUsingId(String userId);

  public Optional<UserDto> findUserByIdAndPassword(UserDto user);

  public void updateUser(UserDto user);

  public void deleteUser(UserDto user, HttpSession session);

}