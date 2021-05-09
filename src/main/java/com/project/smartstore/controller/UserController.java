package com.project.smartstore.controller;


import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.project.smartstore.annotation.LoginCheck;
import com.project.smartstore.dto.UserDto;
import com.project.smartstore.service.LoginService;
import com.project.smartstore.service.UserService;

import lombok.RequiredArgsConstructor;


/**
 * @RestController : @Controller와 @ResponseBody가 합쳐진 기능의 어노테이션입니다.
 *     또한 @RequestMapping 메서드가 @ResponseBody를 수행한다는 의미도 있습니다.
 *
 * @RequiredArgsConstructor :이 어노테이션은 초기화 되지않은 final 필드나, @NonNull 이 붙은 필드에 대해 생성자를 생성해 줍니다.
 *     주로 의존성 주입(Dependency Injection) 편의성을 위해서 사용되곤 합니다.
 *
 * @RequestBody 어노테이션과  @ResponseBody 어노테이션은 각각 HTTP 요청 몸체를 자바 객체로 변환하는 데 사용된다.
 *     이 어노테이션을 이용하면 HTTP 요청 몸체를 자바 객체로 전달받을 수 있다.
 *     비슷하게 @ResponseBody 어노테이션을 이용하면 자바 객체를 HTTP 응답 몸체로 전송할 수 있다.
 *
 * @ResponseStatus : 컨트롤러의 응답상태를 지정하는 어노테이션입니다. code와  reason의 두가지 인자를 사용할 수 있는데,
 *     code를 HttpStatus code로 설정할 수 있습니다. reason을 설정하면 Spring은 HttpServletResponse.sendError()를 호출하고
 *     클라이언트에 HTML 오류 페이지를 전송하기 때문에 REST 엔드 포인트에 적합하지 않습니다.
 *     또한 Spring은 표시된 메소드가 성공적으로 완료 될 때만 @ResponseStatus를 사용 합니다.(Exception 을 던지지 않고).
 */
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;
  private final LoginService sessionLoginService;

  /**
   * 회원가입 메서드.
   */
  @PostMapping("/join")
  @ResponseStatus(HttpStatus.CREATED)
  public void joinUser(@RequestBody UserDto user) {

    userService.joinUser(user);
  }

  /**
   * 로그인 메서드.
   */
  @PostMapping("/login")
  public void loginUser(@RequestBody UserDto user, HttpSession session) {

    sessionLoginService.loginUser(user, session);
  }

  /**
   * 회원정보 수정 메서드.
   */
  @LoginCheck
  @PutMapping("/account")
  public void updateUser(@RequestBody UserDto user) {
    userService.updateUser(user);
  }

  /**
   * 회원삭제(탈퇴) 메서드.
   */
  @LoginCheck
  @DeleteMapping("/account")
  public void deleteUser(@RequestBody UserDto user, HttpSession session) {
    userService.deleteUser(user, session);
  }

  /**
   * 로그아웃 메서드.
   */
  @LoginCheck
  @GetMapping("/logOut")
  public void logOutUser(@RequestBody UserDto user, HttpSession session) {
    sessionLoginService.logOutUser(user, session);
  }
}
