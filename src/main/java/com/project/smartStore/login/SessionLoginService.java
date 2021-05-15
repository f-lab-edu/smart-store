package com.project.smartStore.login;

import com.project.smartStore.customer.CustomerMapper;
import com.project.smartStore.security.EncryptionConverter;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SessionLoginService implements LoginService {

  private final String LOGIN_ATTRIBUTE = "LOGIN";

  private final EncryptionConverter encryptionConverter;

  private final CustomerMapper customerMapper;

  @Override
  public void login(HttpSession httpSession, LoginDTO LoginDTO) {
    String inputPassword = encryptionConverter.ConvertSHA256WithSalt(LoginDTO.getPassword());
    String StoredPaswword = customerMapper.selectCustomerPasswordById(LoginDTO.getId());

    if (inputPassword.equals(StoredPaswword)) {
      httpSession.setAttribute(LOGIN_ATTRIBUTE, LoginDTO.getId());
    }
  }

}
