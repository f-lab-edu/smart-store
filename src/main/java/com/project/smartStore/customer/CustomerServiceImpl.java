package com.project.smartStore.customer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

  private final CustomerMapper customerMapper;

  @Override
  public void registerCustomer(CustomerDTO params) {
      customerMapper.insertCustomer(params);
  }

  @Override
  public CustomerDTO getCustomerDetailById(String id) {
    return customerMapper.selectCustomerDetailById(id);
  }

  @Override
  public void modifiyPwd(String id, String pwd) {
    customerMapper.updateCustomerPwd(id, pwd);
  }

  @Override
  public void modifiyName(String id, String name) {
    customerMapper.updateCustomerName(id, name);
  }

  @Override
  public void modifiyPhoneNum(String id, String phoneNum) {
    customerMapper.updateCustomerPhoneNum(id, phoneNum);
  }

  @Override
  public void deleteCustomer(String id) {
    customerMapper.deleteCustomerById(id);
  }
}
