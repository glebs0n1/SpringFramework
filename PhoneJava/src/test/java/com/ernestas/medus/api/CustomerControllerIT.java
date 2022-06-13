package com.ernestas.medus.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;

import com.ernestas.medus.entities.account.Account;
import com.ernestas.medus.entities.customer.Customer;
import com.ernestas.medus.entities.customer.CustomerCreate;
import com.ernestas.medus.entities.customer.CustomerRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import java.util.ArrayList;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomerControllerIT extends ITBase {

  @Autowired
  private CustomerRepository customerRepository;

  @Test
  public void whenGetCustomersOk() {

    RestAssured.given()
        .accept(ContentType.JSON)
        .contentType(ContentType.JSON)
        .get("http://localhost:8081/customers")
        .then()
        .log()
        .all();

  }

  @Test
  public void whenDeleteCustomerByIdOk() {

    Customer customer = customerRepository.saveAndFlush(
        Customer.builder()
            .companyCode("GoogleCode")
            .companyName("Google")
            .personalCode("99999999999")
            .name("Google")
            .surname("Google")
            .customerAccounts(new ArrayList<>())
            .build()
    );

    RestAssured.given()
        .accept(ContentType.JSON)
        .contentType(ContentType.JSON)
        .delete("http://localhost:8081/customers/" + customer.getId())
        .then()
        .statusCode(200);

  }

  @Test
  public void whenDeleteAccountByIdOk() {

    RestAssured.given()
        .accept(ContentType.JSON)
        .contentType(ContentType.JSON)
        .delete("http://localhost:8081/customers/2/accounts/8")
        .then()
        .statusCode(200);
  }

  @Test
  public void whenDeleteAccountByNonExistentAccountIdThrowsNotFoundException() {

    RestAssured.given()
        .accept(ContentType.JSON)
        .contentType(ContentType.JSON)
        .delete("http://localhost:8081/customers/2/accounts/100")
        .then()
        .statusCode(404);
  }

  @Test
  public void whenDeleteAccountByNonExistentCustomerIdThrowsNotFoundException() {

    RestAssured.given()
        .accept(ContentType.JSON)
        .contentType(ContentType.JSON)
        .delete("http://localhost:8081/customers/200/accounts/6")
        .then()
        .statusCode(404);
  }

  @Test
  public void whenGetCustomerByIdOk() {

    RestAssured.given()
        .accept(ContentType.JSON)
        .contentType(ContentType.JSON)
        .get("http://localhost:8081/customers/2")
        .then()
        .statusCode(200);

  }

  @Test
  public void whenGetNonExistentCustomerByIdThrowsNotFoundError() {

    RestAssured.given()
        .accept(ContentType.JSON)
        .contentType(ContentType.JSON)
        .get("http://localhost:8081/customers/100")
        .then()
        .statusCode(404)
        .body("status", is(404))
        .body("error", is("Not Found"));

  }

  @Test
  public void whenAddAccountOk() {

    RestAssured.given()
        .body(Account.builder()
            .description("New account created for user Phone1")
            .name("Phone1Apple")
            .build()
        )
        .accept(ContentType.JSON)
        .contentType(ContentType.JSON)
        .post("http://localhost:8081/customers/2/accounts")
        .then()
        .statusCode(201);

  }
}
