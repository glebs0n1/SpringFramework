package com.ernestas.medus.api;

import com.ernestas.medus.entities.orderedservice.OrderedServiceCreate;
import com.ernestas.medus.entities.orderedservice.OrderedServiceUpdate;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import java.time.Duration;
import java.time.LocalDateTime;
import org.junit.Test;

public class PhoneNumberControllerIT extends ITBase {

  @Test
  public void whenOrderServiceForPhoneNumberOk() {

    RestAssured.given()
        .accept(ContentType.JSON)
        .contentType(ContentType.JSON)
        .body(OrderedServiceCreate.builder()
            .activeFrom(LocalDateTime.now())
            .activeTo(LocalDateTime.now().plus(Duration.ofDays(50)))
            .name("New order")
            .build())
        .post("http://localhost:9001/phone-numbers/4/services/1/order")
        .then()
        .statusCode(201);

  }

  @Test
  public void whenUpdateOrderedServiceForPhoneNumberOk() {

    RestAssured.given()
        .accept(ContentType.JSON)
        .contentType(ContentType.JSON)
        .body(OrderedServiceUpdate.builder()
            .activeFrom(LocalDateTime.now())
            .activeTo(LocalDateTime.now().plus(Duration.ofDays(50)))
            .name("New order")
            .build())
        .put("http://localhost:9001/phone-numbers/4/services/5")
        .then()
        .statusCode(200);

  }

  @Test
  public void whenTerminateOrderForServiceOk() {
    RestAssured.given()
        .accept(ContentType.JSON)
        .contentType(ContentType.JSON)
        .post("http://localhost:9001/phone-numbers/4/ordered-services/5/terminate")
        .then()
        .statusCode(200);
  }

  @Test
  public void whenGetPhoneNumbersOk() {
    RestAssured.given()
        .accept(ContentType.JSON)
        .contentType(ContentType.JSON)
        .get("http://localhost:9001/phone-numbers")
        .then()
        .statusCode(200);
  }

  @Test
  public void whenGetPhoneNumberByIdOk() {
    RestAssured.given()
        .accept(ContentType.JSON)
        .contentType(ContentType.JSON)
        .get("http://localhost:9001/phone-numbers/4")
        .then()
        .statusCode(200);
  }

}
