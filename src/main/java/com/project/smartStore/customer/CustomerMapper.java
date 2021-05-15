package com.project.smartStore.customer;


import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CustomerMapper {

  CustomerDTO selectCustomerDetailById(String id);

  void insertCustomer(CustomerDTO params);

  void updateCustomerDetail(CustomerDTO customerDTO);

  void deleteCustomerById(String id);
}
