package com.ernestas.medus.helper;

import com.ernestas.medus.entities.account.Account;
import com.ernestas.medus.entities.customer.Customer;
import com.ernestas.medus.entities.customer.CustomerRepository;
import com.ernestas.medus.entities.orderedservice.OrderedService;
import com.ernestas.medus.entities.phonenumber.PhoneNumber;
import com.ernestas.medus.entities.service.BillableService;
import com.ernestas.medus.entities.service.BillableServiceRepository;
import com.ernestas.medus.entities.service.BillableServiceType;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InitialDataCreator {

  @Autowired
  private BillableServiceRepository billableServiceRepository;

  @Autowired
  private CustomerRepository customerRepository;

  @PostConstruct
  public void createData() {

    Customer customer = Customer.builder()
        .companyCode("Zenitech")
        .companyName("Zenitech")
        .personalCode("35712038328")
        .name("Ernestas")
        .surname("Seminogovas")
        .customerAccounts(new ArrayList<>())
        .build();

    Account account = Account.builder()
        .description("first account for user Ernestas Seminogovas")
        .name("wolfas560000")
        .phoneNumbers(new ArrayList<>())
        .customer(customer)
        .build();

    Account secondAccount = Account.builder()
        .description("second account for user Ernestas Seminogovas")
        .name("wolfas560000")
        .phoneNumbers(new ArrayList<>())
        .customer(customer)
        .build();

    BillableService billableService = billableServiceRepository.saveAndFlush(
        BillableService.builder()
            .description("Home internet for Ernestas")
            .billableServiceType(BillableServiceType.SERVICE_ABOARD)
            .name("Excellent internet")
            .build()
    );

    PhoneNumber phoneNumber = PhoneNumber.builder()
        .activeFrom(LocalDateTime.now())
        .activeTo(LocalDateTime.now().plus(Duration.ofDays(20)))
        .account(account)
        .orderedServiceList(new ArrayList<>())
        .phoneDigits("+37061432575")
        .build();

    PhoneNumber secondPhoneNumber = PhoneNumber.builder()
        .activeFrom(LocalDateTime.now())
        .activeTo(LocalDateTime.now().plus(Duration.ofDays(20)))
        .account(account)
        .phoneDigits("+37061432579")
        .orderedServiceList(new ArrayList<>())
        .build();

    PhoneNumber thirdAccountNumber = PhoneNumber.builder()
        .activeFrom(LocalDateTime.now())
        .activeTo(LocalDateTime.now().plus(Duration.ofDays(20)))
        .account(account)
        .phoneDigits("+37061432578")
        .orderedServiceList(new ArrayList<>())
        .build();

    OrderedService orderedService = OrderedService.builder()
        .name("My internet")
        .activeFrom(LocalDateTime.now())
        .activeTo(LocalDateTime.now().plus(Duration.ofDays(20)))
        .billableService(billableService)
        .phoneNumber(phoneNumber)
        .build();

    phoneNumber.getOrderedServiceList().add(orderedService);
    account.getPhoneNumbers().add(phoneNumber);
    account.getPhoneNumbers().add(secondPhoneNumber);
    account.getPhoneNumbers().add(thirdAccountNumber);

    customer.getCustomerAccounts().add(account);
    customer.getCustomerAccounts().add(secondAccount);

    customerRepository.saveAndFlush(customer);

  }

}
