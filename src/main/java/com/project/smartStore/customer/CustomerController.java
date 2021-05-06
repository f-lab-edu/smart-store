package com.project.smartStore.customer;

import com.project.smartStore.login.LoginDTO;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customers")
public class CustomerController {

  private final CustomerService customerService;

  @PostMapping("/login")
  public void LoginCustomer(@RequestBody LoginDTO loginDTO, HttpSession session){
    customerService.loginCustomer(loginDTO, session);
  }

  @PostMapping
  public void registerCustomer(@RequestBody CustomerDTO customerDTO) {
    customerService.registerCustomer(customerDTO);
  }

  @GetMapping("/{id}")
  public CustomerDTO getCustomerDetail(@PathVariable String id) {
    return customerService.getCustomerDetailById(id);
  }

  @PatchMapping("/{id}")
  public void modifyCustomer(@PathVariable String id, @RequestBody CustomerDTO customerDTO) {
    if (customerDTO.getName() != null) {
      customerService.modifiyName(id, customerDTO.getName());
    }
    if (customerDTO.getPassword() != null) {
      customerService.modifiyPwd(id, customerDTO.getPassword());
    }
    if (customerDTO.getPhoneNum() != null) {
      customerService.modifiyPhoneNum(id, customerDTO.getPhoneNum());
    }
  }

  @DeleteMapping("/{id}")
  public void deleteCustomer(@PathVariable String id) {
    customerService.deleteCustomer(id);
  }
}
