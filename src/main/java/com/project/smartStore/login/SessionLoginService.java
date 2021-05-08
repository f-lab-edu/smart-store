package com.project.smartStore.login;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

@Service
public class SessionLoginService {

  private final String LOGIN_ATTRIBUTE = "LOGIN";

  public void login(HttpSession session, String id) {
    session.setAttribute(LOGIN_ATTRIBUTE, id);
  }

}
