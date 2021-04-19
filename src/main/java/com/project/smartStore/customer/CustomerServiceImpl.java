package com.project.smartStore.customer;

import com.project.smartStore.security.EncryptionConverter;
import java.security.NoSuchAlgorithmException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

  private final CustomerMapper customerMapper;
  private final EncryptionConverter encryptionConverter;

  @Override
  public void registerCustomer(CustomerDTO customer) {
    try {
      String encryptionPassword = encryptionConverter.ConvertSHA256WithSalt(customer.getPassword());
      customer.setPassword(encryptionPassword);
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException(e);
    }
    customerMapper.insertCustomer(customer);
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