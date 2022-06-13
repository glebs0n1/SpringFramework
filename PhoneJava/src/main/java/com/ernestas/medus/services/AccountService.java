package com.ernestas.medus.services;

import com.ernestas.medus.entities.account.Account;
import com.ernestas.medus.entities.account.AccountRepository;
import com.ernestas.medus.entities.account.AccountUpdate;
import com.ernestas.medus.entities.phonenumber.PhoneNumber;
import com.ernestas.medus.entities.phonenumber.PhoneNumberCreate;
import com.ernestas.medus.entities.phonenumber.PhoneNumberRepository;
import com.ernestas.medus.error.BadRequestException;
import com.ernestas.medus.error.NotFoundException;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

  private final AccountRepository accountRepository;

  private final PhoneNumberRepository phoneNumberRepository;

  @Autowired
  public AccountService(AccountRepository accountRepository,
      PhoneNumberRepository phoneNumberRepository) {
    this.accountRepository = accountRepository;
    this.phoneNumberRepository = phoneNumberRepository;
  }

  public List<Account> getAccounts() {
    return accountRepository.findAll();
  }

  public void updateAccount(Long id, AccountUpdate accountUpdate) {

    Account currentAccount = accountRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("Account not found"));

    currentAccount.setDescription(accountUpdate.getDescription());
    currentAccount.setName(accountUpdate.getName());

    accountRepository.saveAndFlush(currentAccount);

  }

  public Account getAccountById(Long id) {
    return accountRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("Account not found"));
  }

  @Transactional
  public void createPhoneNumberForAccount(Long accountId,
      PhoneNumberCreate phoneNumberCreate) {

    Account account = accountRepository.findById(accountId).orElseThrow(() ->
        new NotFoundException("Account not found"));

    PhoneNumber phoneNumber = PhoneNumber.builder()
        .account(account)
        .activeFrom(phoneNumberCreate.getActiveFrom())
        .activeTo(phoneNumberCreate.getActiveTo())
        .phoneDigits(phoneNumberCreate.getPhoneDigits())
        .build();

    account.getPhoneNumbers().add(phoneNumber);

    accountRepository.saveAndFlush(account);
  }


  @Transactional
  public void deletePhoneNumberForAccount(Long accountId, Long phoneNumberId) {

    Account account = accountRepository.findById(accountId).orElseThrow(() ->
        new NotFoundException("Account does not exist"));

    PhoneNumber phoneNumber = phoneNumberRepository.findById(phoneNumberId).orElseThrow(() ->
        new NotFoundException("Phone number not found"));

    if (!phoneNumber.getOrderedServiceList().isEmpty()) {
      throw new BadRequestException(
          "Phone number cannot be deleted because it has ordered services");
    }

    if (account.getPhoneNumbers().contains(phoneNumber)) {
      account.getPhoneNumbers().remove(phoneNumber);
      accountRepository.saveAndFlush(account);
    } else {
      throw new BadRequestException("Phone number does not belong to account");

    }

  }


}
