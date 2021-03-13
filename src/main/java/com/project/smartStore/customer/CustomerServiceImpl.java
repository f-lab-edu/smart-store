package com.project.smartStore.customer;

import java.sql.SQLIntegrityConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService{

  @Autowired
  private CustomerMapper customerMapper;

  @Override
  public boolean registerCustomer(CustomerDTO params){
    try{
      customerMapper.insertCustomer(params);
    }catch (Exception e){
      e.printStackTrace();
      return false;
    }
    return true;
  }
  @Override
  public CustomerDTO getCustomerDetailById(String id) {
    return customerMapper.selectCustomerDetailById(id);
  }

  @Override
  public boolean modifiyPwd(String id, String pwd) {
    int result = customerMapper.updateCustomerPwd(id, pwd);
    return result == 1;
  }

  @Override
  public boolean modifiyName(String id, String name) {
    int result = customerMapper.updateCustomerName(id, name);
    return result == 1;
  }

  @Override
  public boolean modifiyPhoneNum(String id, String phoneNum) {
    int result = customerMapper.updateCustomerPhoneNum(id, phoneNum);
    return result == 1;
  }


  @Override
  public boolean deleteCustomer(String id) {
    int result = customerMapper.deleteCustomerById(id);
    return result == 1;
  }
}
