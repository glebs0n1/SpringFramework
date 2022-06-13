package com.company.PhoneJava.api;

import com.company.PhoneJava.entities.service.BillableService;
import com.company.PhoneJava.entities.service.BillableServiceCreate;
import com.company.PhoneJava.entities.service.BillableServiceUpdate;
import com.company.PhoneJava.services.BillableServiceService;

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
@RequestMapping("/billable-services")
public class BillableServiceController {

  private BillableServiceService billableServiceService;

  @Autowired
  public BillableServiceController(BillableServiceService billableServiceService) {
    this.billableServiceService = billableServiceService;
  }

  @GetMapping
  public List< BillableService > getBillableServices() {
    return billableServiceService.getAllBillableServices();
  }

  @GetMapping("/{billableServiceId}")
  public BillableService getBillableServiceById(
      @PathVariable("billableServiceId") Long billableServiceId) {
    return billableServiceService.getBillableServiceById(billableServiceId);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void createBillableService(@Valid @RequestBody BillableServiceCreate billableServiceCreate) {
    billableServiceService.createBillableService(billableServiceCreate);
  }

  @DeleteMapping("/{billableServiceId}")
  public void deleteBillableService(@PathVariable("billableServiceId") Long billableServiceId) {
    billableServiceService.deleteBillableServiceById(billableServiceId);
  }

  @PutMapping("/{billableServiceId}")
  public void updateService(@PathVariable("billableServiceId") Long billableServiceId,
      @Valid @RequestBody BillableServiceUpdate billableServiceUpdate) {
    billableServiceService.updateBillableService(billableServiceId, billableServiceUpdate);
  }


}
