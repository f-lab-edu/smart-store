package com.project.smartstore.service;

import com.project.smartstore.dto.UserDto;
import java.util.Optional;


public interface UserService {

  void joinUser(UserDto user);

  boolean isUsingId(String userId);

  Optional<UserDto> findUserByIdAndPassword(UserDto user);

  UserDto findUserById(String userId);

  void updateUser(UserDto user);

  void deleteUser(UserDto user);

}