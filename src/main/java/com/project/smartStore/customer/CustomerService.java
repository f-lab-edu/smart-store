package com.project.smartStore.customer;

public interface CustomerService {

  boolean registerCustomer(CustomerDTO params);

  CustomerDTO getCustomerDetailById(String id);

  boolean modifiyPwd(String email, String pwd);

  boolean modifiyName(String email, String name);

  boolean modifiyPhoneNum(String email, String phoneNum);

  boolean deleteCustomer(String email);
}
