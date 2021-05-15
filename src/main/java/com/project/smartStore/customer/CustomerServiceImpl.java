package com.project.smartStore.customer;

import com.project.smartStore.security.EncryptionConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

  private final CustomerMapper customerMapper;

  private final EncryptionConverter encryptionConverter;

  @Override
  public void registerCustomer(CustomerDTO customer) {
    String encryptionPassword = encryptionConverter.ConvertSHA256WithSalt(customer.getPassword());
    CustomerDTO encryptionCustomerDto = new CustomerDTO(customer.getId(), encryptionPassword,
        customer.getName(),
        customer.getPhoneNum());
    customerMapper.insertCustomer(encryptionCustomerDto);
  }

  @Override
  public CustomerDTO getCustomerDetailById(String id) {
    return customerMapper.selectCustomerDetailById(id);
  }

  @Override
  public void modifiyCustomerDetail(CustomerDTO customerDTO) {
    customerMapper.updateCustomerDetail(customerDTO);
  }

  @Override
  public void deleteCustomer(String id) {
    customerMapper.deleteCustomerById(id);
  }
}