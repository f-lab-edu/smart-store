package com.project.smartstore.interceptor;

import com.project.smartstore.annotation.LoginCheck;
import com.project.smartstore.exception.UnAuthenticatedAccessException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;


/**
 * 로그인체크 인터셉터.
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

  public static String LOGIN_ID = "loginId";

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, 
      Object handler) throws Exception {
    
    HttpSession session = request.getSession();
    String loginId = (String) session.getAttribute(LOGIN_ID);
    HandlerMethod handlerMethod = (HandlerMethod) handler;
    LoginCheck ddd = handlerMethod.getMethodAnnotation(LoginCheck.class);
    
    if (handlerMethod.hasMethodAnnotation(LoginCheck.class) && loginId == null) {
      throw new UnAuthenticatedAccessException("인가되지 않은 접근입니다.");
    }
    
    return true;
  }
}
