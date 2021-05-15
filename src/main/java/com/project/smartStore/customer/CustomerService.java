package com.project.smartStore.customer;

public interface CustomerService {

  void registerCustomer(CustomerDTO params);

  CustomerDTO getCustomerDetailById(String id);

  void modifiyCustomerDetail(CustomerDTO customerDTO);

  void deleteCustomer(String email);
}
