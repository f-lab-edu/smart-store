package com.project.smartstore.service;

import com.project.smartstore.dto.UserDto;

public interface LoginService {

  void login(UserDto user);

  void logout();

  String getLoginId();

  UserDto getLoginUserInfo();
}
