package com.project.smartstore.service;

import com.project.smartstore.dto.UserDto;
import com.project.smartstore.exception.NoneExistentUserException;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class SessionLoginService implements LoginService {

  private final UserServiceImpl userService;

  @Override
  public void login(UserDto user, HttpSession session) {
    Optional<UserDto> result = userService.findUserByIdAndPassword(user);

    if (!result.isPresent()) {
      throw new NoneExistentUserException("존재하지 않는 사용자입니다.");
    }

    session.setAttribute(UserServiceImpl.LOGIN_ID, result.get().getId());
  }

  @Override
  public void logout(UserDto user, HttpSession session) {
    session.invalidate();
  }
}
