package com.ernestas.medus.api;

import com.ernestas.medus.entities.account.AccountCreate;
import com.ernestas.medus.entities.customer.Customer;
import com.ernestas.medus.entities.customer.CustomerCreate;
import com.ernestas.medus.entities.customer.CustomerUpdate;
import com.ernestas.medus.services.CustomerService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerController {

  private CustomerService customerService;

  @Autowired
  public CustomerController(CustomerService customerService) {
    this.customerService = customerService;
  }

  @GetMapping
  public List<Customer> getCustomers() {
    return customerService.getAllCustomers();
  }

  @GetMapping("/{customerId}")
  public Customer getCustomer(@PathVariable("customerId") Long customerId) {
     return customerService.getCustomerById(customerId);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void createCustomer(@RequestBody @Valid CustomerCreate customerCreate) {
    customerService.createCustomer(customerCreate);
  }

  @PutMapping("/{customerId}")
  public void updateCustomer(@PathVariable("customerId") Long customerId, @Valid @RequestBody CustomerUpdate customerUpdate) {
    customerService.updateCustomer(customerId, customerUpdate);
  }

  @PostMapping("/{customerId}/accounts")
  @ResponseStatus(HttpStatus.CREATED)
  public void createAccount(@PathVariable("customerId") Long customerId, @Valid @RequestBody AccountCreate accountCreate) {
    customerService.addAccount(customerId, accountCreate);
  }

  @DeleteMapping("/{customerId}/accounts/{accountId}")
  public void deleteAccount(@PathVariable("customerId") Long customerId, @PathVariable("accountId") Long accountId) {
    customerService.deleteAccount(customerId, accountId);
  }

  @DeleteMapping("/{customerId}")
  public void deleteCustomer(@PathVariable("customerId") Long customerId) {
    customerService.deleteCustomerById(customerId);
  }

}
