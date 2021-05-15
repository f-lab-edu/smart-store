package com.project.smartStore.customer;

import com.project.smartStore.security.EncryptionConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customers")
public class CustomerController {

  private final CustomerService customerService;

  private final EncryptionConverter encryptionConverter;

  @PostMapping
  public void registerCustomer(@RequestBody CustomerDTO customerDTO) {
    customerService.registerCustomer(customerDTO);
  }

  @GetMapping("/{id}")
  public CustomerDTO getCustomerDetail(@PathVariable String id) {
    return customerService.getCustomerDetailById(id);
  }

  @PutMapping
  public void modifyCustomerDetail(@RequestBody CustomerDTO customerDTO) {
    String encryptPwd = encryptionConverter.ConvertSHA256WithSalt(customerDTO.getPassword());
    CustomerDTO encryptCustomerDTO = new CustomerDTO(customerDTO.getId(), encryptPwd,
        customerDTO.getName(), customerDTO.getPhoneNum());
    customerService.modifiyCustomerDetail(encryptCustomerDTO);
  }

  @DeleteMapping("/{id}")
  public void deleteCustomer(@PathVariable String id) {
    customerService.deleteCustomer(id);
  }
}
