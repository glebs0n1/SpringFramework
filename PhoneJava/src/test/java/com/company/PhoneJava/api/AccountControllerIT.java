package com.company.PhoneJava.api;

import static org.assertj.core.api.Assertions.assertThat;

import com.company.PhoneJava.entities.account.AccountRepository;
import com.company.PhoneJava.entities.account.AccountUpdate;
import com.company.PhoneJava.entities.phonenumber.PhoneNumberCreate;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import java.time.Duration;
import java.time.LocalDateTime;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class AccountControllerIT extends ITBase {

  @Autowired
  private AccountRepository accountRepository;

  @Test
  public void whenGetAccountsOk() {

    RestAssured.given()
        .accept(ContentType.JSON)
        .contentType(ContentType.JSON)
        .get("http://localhost:8081/accounts")
        .then()
        .statusCode(200);
  }

  @Test
  public void whenGetAccountByIdOk() {

    RestAssured.given()
        .accept(ContentType.JSON)
        .contentType(ContentType.JSON)
        .get("http://localhost:8081/accounts/3")
        .then()
        .statusCode(200);
  }

  @Test
  public void whenUpdateAccountOk() {

    RestAssured.given()
        .accept(ContentType.JSON)
        .contentType(ContentType.JSON)
        .body(AccountUpdate.builder()
            .description("Upgraded account for Phone1")
            .name("More flexible")
            .build())
        .put("http://localhost:8081/accounts/3")
        .then()
        .statusCode(200);

    assertThat(accountRepository.findById(3L).get().getName())
        .isEqualTo("More flexible");
    assertThat(accountRepository.findById(3L).get().getDescription())
        .isEqualTo("Upgraded account for Phone1");
  }

  @Test
  public void whenCreatePhoneNumberForAccountOk() {

    RestAssured.given()
        .accept(ContentType.JSON)
        .contentType(ContentType.JSON)
        .body(PhoneNumberCreate.builder()
            .activeTo(LocalDateTime.now().plus(Duration.ofDays(20)))
            .activeFrom(LocalDateTime.now())
            .phoneDigits("+37099999999")
            .build()
        )
        .post("http://localhost:8081/accounts/3/phone-numbers")
        .then()
        .statusCode(201);

  }


  @Test
  public void whenDeletePhoneNumberForAccountOk() {

    RestAssured.given()
        .accept(ContentType.JSON)
        .contentType(ContentType.JSON)
        .delete("http://localhost:8081/accounts/3/phone-numbers/7")
        .then()
        .statusCode(200);

  }

}
