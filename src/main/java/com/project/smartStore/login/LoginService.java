package com.project.smartStore.login;

import javax.servlet.http.HttpSession;

public interface LoginService {

  void login(HttpSession httpSession, LoginDTO loginDTO);

}
