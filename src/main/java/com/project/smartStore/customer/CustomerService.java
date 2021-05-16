package com.project.smartStore.customer;

public interface CustomerService {

  void registerCustomer(CustomerDTO params);

  CustomerDTO getCustomerDetailById(String id);

  String getCustomerPassword(String id);

  void modifiyPwd(String email, String pwd);

  void modifiyName(String email, String name);

  void modifiyPhoneNum(String email, String phoneNum);

  void deleteCustomer(String email);
}
