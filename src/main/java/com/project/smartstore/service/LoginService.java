package com.project.smartstore.service;

import com.project.smartstore.dto.UserDto;
import javax.servlet.http.HttpSession;

/**
 * 로그인 관련 로직을 처리하는 서비스.
 */
public interface LoginService {

  public void login(UserDto user, HttpSession session);

  public void logOut(UserDto user, HttpSession session);
}
