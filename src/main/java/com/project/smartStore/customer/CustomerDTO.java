package com.project.smartStore.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CustomerDTO {

  private String id;
  private String password;
  private String name;
  private String phoneNum;
}
