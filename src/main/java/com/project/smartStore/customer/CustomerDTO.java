package com.project.smartStore.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CustomerDTO {

  private String id;
  @Setter
  private String password;
  private String name;
  private String phoneNum;
}
