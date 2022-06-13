package com.ernestas.medus.services;

import com.ernestas.medus.entities.account.Account;
import com.ernestas.medus.entities.account.AccountCreate;
import com.ernestas.medus.entities.account.AccountRepository;
import com.ernestas.medus.entities.customer.Customer;
import com.ernestas.medus.entities.customer.CustomerCreate;
import com.ernestas.medus.entities.customer.CustomerRepository;
import com.ernestas.medus.entities.customer.CustomerUpdate;
import com.ernestas.medus.error.NotFoundException;
import com.ernestas.medus.validators.PersonalCodeValidator;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

  @Autowired
  public CustomerService(CustomerRepository customerRepository,
      AccountRepository accountRepository, PersonalCodeValidator personalCodeValidator) {
    this.customerRepository = customerRepository;
    this.accountRepository = accountRepository;
    this.personalCodeValidator = personalCodeValidator;
  }

  private CustomerRepository customerRepository;

  private AccountRepository accountRepository;

  private PersonalCodeValidator personalCodeValidator;

  @Transactional
  public void createCustomer(CustomerCreate customerCreate) {

    personalCodeValidator.validate(customerCreate.getPersonalCode());

    customerRepository.saveAndFlush(
        Customer.builder()
            .name(customerCreate.getName())
            .surname(customerCreate.getSurname())
            .personalCode(customerCreate.getPersonalCode())
            .companyName(customerCreate.getCompanyName())
            .companyCode(customerCreate.getCompanyCode())
            .build()
    );
  }

  public List<Customer> getAllCustomers() {
    return customerRepository.findAll();
  }

  public Customer getCustomerById(Long id) {
    return customerRepository.findById(id).orElseThrow(() -> new NotFoundException(
        "Customer not found"));
  }

  @Transactional
  public void updateCustomer(Long id, CustomerUpdate customerUpdate) {

    personalCodeValidator.validate(customerUpdate.getPersonalCode());

    Customer currentCustomer = customerRepository.findById(id).orElseThrow(
        () -> new NotFoundException("Customer not found"));

    currentCustomer.setCompanyCode(customerUpdate.getCompanyCode());
    currentCustomer.setCompanyName(customerUpdate.getCompanyName());
    currentCustomer.setName(customerUpdate.getName());
    currentCustomer.setSurname(customerUpdate.getSurname());
    currentCustomer.setPersonalCode(customerUpdate.getPersonalCode());

    customerRepository.saveAndFlush(currentCustomer);
  }

  @Transactional
  public void addAccount(Long id, AccountCreate accountCreate) {
    Customer customer = customerRepository.findById(id).orElseThrow(
        () -> new NotFoundException("Customer not found"));

    Account account = Account.builder()
        .description(accountCreate.getDescription())
        .name(accountCreate.getName())
        .customer(customer)
        .build();

    customer.getCustomerAccounts().add(account);

    customerRepository.saveAndFlush(customer);
  }


  public void deleteAccount(Long id, Long accountId) {

    Customer customer = customerRepository.findById(id).orElseThrow(
        () -> new NotFoundException("Customer not found"));

    Account account = accountRepository.findById(accountId).orElseThrow(
        () -> new NotFoundException("Account not found"));

    if (customer.getCustomerAccounts().contains(account)) {
      accountRepository.deleteById(accountId);
    }

  }

  public void deleteCustomerById(Long id) {
    customerRepository.deleteById(id);
  }

}
