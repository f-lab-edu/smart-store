package com.project.smartStore.customer;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {

  private final CustomerService customerService;

  public CustomerController(CustomerService customerService) {
    this.customerService = customerService;
  }

  @PostMapping
  public boolean registerCustomer(@RequestBody CustomerDTO customerDTO){
    return customerService.registerCustomer(customerDTO);
  }

  @GetMapping("/{id}")
  public CustomerDTO getCustomerDetail(@PathVariable String id){
    return customerService.getCustomerDetailById(id);
  }

  @PutMapping("/pwd/{id}")
  public boolean modifyCustomerPwd(@PathVariable String id, @RequestBody CustomerDTO customerDTO){
    return customerService.modifiyPwd(id, customerDTO.getPassword());
  }

  @PutMapping("/name/{id}")
  public boolean modifyCustomerName(@PathVariable String id, @RequestBody CustomerDTO customerDTO){
    return customerService.modifiyName(id, customerDTO.getName());
  }

  @PutMapping("/phone-num/{id}")
  public boolean modifyCustomerPhoneNum(@PathVariable String id, @RequestBody CustomerDTO customerDTO){
    return customerService.modifiyPhoneNum(id, customerDTO.getPhoneNum());
  }

  @DeleteMapping("/{id}")
  public boolean deleteCustomer(@PathVariable String id){
    return customerService.deleteCustomer(id);
  }

}
