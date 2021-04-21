package com.project.smartStore.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CustomerDTO {

  private String id;
  private String password;
  private String name;
  private String phoneNum;
}
