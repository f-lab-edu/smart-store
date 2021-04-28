package com.project.smartStore.customer;

import javax.servlet.http.HttpSession;

public interface CustomerService {

  void loginCustomer(LoginDTO loginDTO, HttpSession session);

  String getCustomerPassword(String id);

  void registerCustomer(CustomerDTO params);

  CustomerDTO getCustomerDetailById(String id);

  void modifiyPwd(String email, String pwd);

  void modifiyName(String email, String name);

  void modifiyPhoneNum(String email, String phoneNum);

  void deleteCustomer(String email);
}
