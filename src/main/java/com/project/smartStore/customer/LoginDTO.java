package com.project.smartStore.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
public class LoginDTO {

  private String id;

  private String password;

}
