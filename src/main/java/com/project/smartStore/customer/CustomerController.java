package com.project.smartStore.customer;

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
@RequestMapping("/customer")
public class CustomerController {

  private final CustomerService customerService;

  @PostMapping
  public void registerCustomer(@RequestBody CustomerDTO customerDTO) {
    customerService.registerCustomer(customerDTO);
  }

  @GetMapping("/{id}")
  public CustomerDTO getCustomerDetail(@PathVariable String id) {
    return customerService.getCustomerDetailById(id);
  }

  @PutMapping("/pwd/{id}")
  public void modifyCustomerPwd(@PathVariable String id, @RequestBody CustomerDTO customerDTO) {
    customerService.modifiyPwd(id, customerDTO.getPassword());
  }

  @PutMapping("/name/{id}")
  public void modifyCustomerName(@PathVariable String id, @RequestBody CustomerDTO customerDTO) {
    customerService.modifiyName(id, customerDTO.getName());
  }

  @PutMapping("/phone-num/{id}")
  public void modifyCustomerPhoneNum(@PathVariable String id,
      @RequestBody CustomerDTO customerDTO) {
    customerService.modifiyPhoneNum(id, customerDTO.getPhoneNum());
  }

  @DeleteMapping("/{id}")
  public void deleteCustomer(@PathVariable String id) {
    customerService.deleteCustomer(id);
  }
}
