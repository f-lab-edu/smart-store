package com.project.smartstore.service;

import com.project.smartstore.dto.UserDto;
import javax.servlet.http.HttpSession;

public interface LoginService {

  public void login(UserDto user, HttpSession session);

  public void logOut(UserDto user, HttpSession session);
}
