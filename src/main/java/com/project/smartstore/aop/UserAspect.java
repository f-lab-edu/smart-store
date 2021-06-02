package com.project.smartstore.aop;

import com.project.smartstore.annotation.LoginUserId;
import com.project.smartstore.constants.SessionLoginConstant;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;



@Aspect
@Component
@RequiredArgsConstructor
public class UserAspect {

  private final HttpSession session;

  @Around("execution(* *(.., @com.project.smartstore.annotation.LoginUserId (String), ..))")
  public Object injectUserInfo(ProceedingJoinPoint joinPoint) throws Throwable {

    String loginId = (String) session.getAttribute(SessionLoginConstant.LOGIN_ID);

    Object[] args = joinPoint.getArgs();
    MethodSignature signature = (MethodSignature) joinPoint.getSignature();
    Method method = signature.getMethod();

    Annotation[][] annotations = method.getParameterAnnotations();

    for (int i = 0; i < annotations.length; i++) {
      for (Annotation annotation : annotations[i]) {
        if (annotation instanceof LoginUserId) {
          args[i] = loginId;
          break;
        }
      }
    }

    return joinPoint.proceed(args);
  }
}
