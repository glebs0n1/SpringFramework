package com.ernestas.medus.api;

import com.ernestas.medus.entities.account.Account;
import com.ernestas.medus.entities.account.AccountUpdate;
import com.ernestas.medus.entities.phonenumber.PhoneNumberCreate;
import com.ernestas.medus.services.AccountService;
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
@RequestMapping("/accounts")
public class AccountController {

  @Autowired
  public AccountController(AccountService accountService) {
    this.accountService = accountService;
  }

  private AccountService accountService;

  @GetMapping
  public List<Account> getAccounts() {
    return accountService.getAccounts();
  }

  @GetMapping("/{accountId}")
  public Account getAccountById(@PathVariable ("accountId") Long accountId) {
    return accountService.getAccountById(accountId);
  }

  @PutMapping("/{accountId}")
  public void updateAccount(@PathVariable ("accountId") Long accountId, @Valid @RequestBody AccountUpdate accountUpdate) {
    accountService.updateAccount(accountId,accountUpdate);
  }

  @PostMapping("/{accountId}/phone-numbers")
  @ResponseStatus(HttpStatus.CREATED)
  public void createPhoneNumberForAccount(@PathVariable ("accountId") Long accountId, @Valid @RequestBody
      PhoneNumberCreate phoneNumberCreate) {
    accountService.createPhoneNumberForAccount(accountId, phoneNumberCreate);
  }

  @DeleteMapping("/{accountId}/phone-numbers/{phoneId}")
  public void deletePhoneNumberForAccount(@PathVariable ("accountId") Long accountId, @PathVariable
      ("phoneId") Long phoneNumberId) {
    accountService.deletePhoneNumberForAccount(accountId,phoneNumberId);
  }

}


