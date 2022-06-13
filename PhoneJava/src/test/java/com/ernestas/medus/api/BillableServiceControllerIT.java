package com.ernestas.medus.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;

import com.ernestas.medus.entities.service.BillableService;
import com.ernestas.medus.entities.service.BillableServiceCreate;
import com.ernestas.medus.entities.service.BillableServiceRepository;
import com.ernestas.medus.entities.service.BillableServiceType;
import com.ernestas.medus.entities.service.BillableServiceUpdate;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class BillableServiceControllerIT extends ITBase {

  @Autowired
  private BillableServiceRepository billableServiceRepository;

  @Test
  public void getGetBillableServicesOk() {

    RestAssured.given()
        .accept(ContentType.JSON)
        .contentType(ContentType.JSON)
        .get("http://localhost:8081/billable-services")
        .then()
        .log().all()
        .statusCode(200);

  }

  @Test
  public void getGetBillableServicesByIdOk() {

    RestAssured.given()
        .accept(ContentType.JSON)
        .contentType(ContentType.JSON)
        .get("http://localhost:8081/billable-services/1")
        .then()
        .statusCode(200)
        .body("name", is("Excellent internet"))
        .body("description", is("Home internet for Ernestas"))
        .body("billableServiceType", is(BillableServiceType.SERVICE_ABOARD.name()));

  }

  @Test
  public void getGetBillableServicesByNonExistentIdThrowsNotFoundError() {

    RestAssured.given()
        .accept(ContentType.JSON)
        .contentType(ContentType.JSON)
        .get("http://localhost:8081/billable-services/100")
        .then()
        .statusCode(404)
        .body("status", is(404));

  }

  @Test
  public void whenCreateBillableServiceOk() {
    RestAssured.given()
        .accept(ContentType.JSON)
        .contentType(ContentType.JSON)
        .body(BillableServiceCreate.builder().name("New service")
            .description("Even better internet for your excellent service")
            .billableServiceType(BillableServiceType.HOME_INTERNET)
            .build())
        .post("http://localhost:8081/billable-services")
        .then()
        .statusCode(201);

    assertThat(billableServiceRepository.findAll().size()).isEqualTo(2);

  }

  @Test
  public void whenDeleteBillableServiceOk() {

    BillableService billableService = billableServiceRepository
        .saveAndFlush(BillableService.builder()
            .name("New service")
            .billableServiceType(BillableServiceType.HOME_INTERNET)
            .description("Just a dummy service for testing").build());

    RestAssured.given()
        .accept(ContentType.JSON)
        .contentType(ContentType.JSON)
        .delete("http://localhost:8081/billable-services/" + billableService.getId())
        .then()
        .statusCode(200);

  }

  @Test
  public void whenUpdateBillableServiceOk() {

    BillableService billableService = billableServiceRepository
        .saveAndFlush(BillableService.builder()
            .name("New service name")
            .billableServiceType(BillableServiceType.HOME_INTERNET)
            .description("Just a dummy service for testing").build());

    RestAssured.given()
        .accept(ContentType.JSON)
        .contentType(ContentType.JSON)
        .body(
            BillableServiceUpdate.builder()
                .description(billableService.getDescription())
                .billableServiceType(billableService.getBillableServiceType())
                .name("Excellent new name")
                .build()
        )
        .put("http://localhost:8081/billable-services/" + billableService.getId())
        .then()
        .statusCode(200);

    assertThat(billableServiceRepository
        .findById(billableService.getId()).get().getName())
        .isEqualTo(
            "Excellent new name"
        );
  }

}
