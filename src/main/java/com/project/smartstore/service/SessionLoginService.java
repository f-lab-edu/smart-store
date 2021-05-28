package com.project.smartstore.service;


import com.project.smartstore.constants.SessionLoginConstant;
import com.project.smartstore.dto.UserDto;
import com.project.smartstore.exception.NoneExistentUserException;
import com.project.smartstore.exception.UnAuthenticatedAccessException;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class SessionLoginService implements LoginService {

  private final HttpSession session;
  private final UserServiceImpl userService;

  @Override
  public void login(UserDto user) {
    Optional<UserDto> result = userService.findUserByIdAndPassword(user);

    if (!result.isPresent()) {
      throw new NoneExistentUserException("존재하지 않는 사용자입니다.");
    }

    session.setAttribute(SessionLoginConstant.LOGIN_ID, result.get().getId());
  }

  @Override
  public void logout() {
    session.invalidate();
  }

  @Override
  public String getLoginId() {
    String loginId = (String) session.getAttribute(SessionLoginConstant.LOGIN_ID);

    if (loginId == null) {
      throw new UnAuthenticatedAccessException();
    }
    return loginId;
  }
}
