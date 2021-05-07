package com.project.smartstore.config;

import com.project.smartstore.interceptor.LoginInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 로그인 인터셉터 설정. 
 */
@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer{
  
  private final LoginInterceptor loginInterceptor;
  
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(loginInterceptor);
  }
}
