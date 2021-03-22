package com.project.smartStore.customer;

import java.sql.SQLIntegrityConstraintViolationException;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CustomerMapper {

  CustomerDTO selectCustomerDetailById(String id);

  void insertCustomer(CustomerDTO params) throws SQLIntegrityConstraintViolationException;

  int updateCustomerPwd(String id, String updatePwd);

  int updateCustomerName(String id, String updateName);

  int updateCustomerPhoneNum(String id, String updatePhoneNum);

  int deleteCustomerById(String id);
}
