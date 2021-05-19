package com.project.smartstore.service;

import com.project.smartstore.dto.UserDto;
import javax.servlet.http.HttpSession;

public interface LoginService {

  void login(UserDto user, HttpSession session);

  void logout(UserDto user, HttpSession session);

  String getLoginId(HttpSession session);
}
