package com.project.smartStore.login;

import javax.servlet.http.HttpSession;

public class SessionLoginService {

  private final String LOGIN_ATTRIBUTE = "LOGIN";

  public void login(HttpSession session, String id) {
    session.setAttribute(LOGIN_ATTRIBUTE, id);
  }

}
