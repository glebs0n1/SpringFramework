package com.company.PhoneJava.api;

import com.company.PhoneJava.entities.orderedservice.OrderedServiceCreate;
import com.company.PhoneJava.entities.orderedservice.OrderedServiceUpdate;
import com.company.PhoneJava.entities.phonenumber.PhoneNumber;
import com.company.PhoneJava.services.PhoneNumberService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/phone-numbers")
public class PhoneNumberController {

  private PhoneNumberService phoneNumberService;

  @Autowired
  public PhoneNumberController(PhoneNumberService phoneNumberService) {
    this.phoneNumberService = phoneNumberService;
  }

  @GetMapping
  public List< PhoneNumber > getPhoneNumbers() {
    return phoneNumberService.getPhoneNumbers();
  }

  @GetMapping("/{phoneId}")
  public PhoneNumber getPhoneNumberById(@PathVariable ("phoneId") Long phoneId) {
    return phoneNumberService.getPhoneNumberById(phoneId);
  }

  @PostMapping("/{phoneId}/services/{serviceId}/order")
  @ResponseStatus(HttpStatus.CREATED)
  public void orderService(@PathVariable("phoneId") Long phoneId,
      @PathVariable("serviceId") Long serviceId,
      @Valid @RequestBody OrderedServiceCreate orderedServiceCreate) {
    phoneNumberService.orderServiceForNumber(phoneId, serviceId, orderedServiceCreate);
  }

  @PutMapping("/{phoneId}/services/{serviceId}")
  public void updateService(@PathVariable("phoneId") Long phoneId,
      @PathVariable("serviceId") Long serviceId,
      @Valid @RequestBody OrderedServiceUpdate orderedServiceUpdate) {
    phoneNumberService.updateServiceForNumber(phoneId, serviceId, orderedServiceUpdate);
  }

  @PostMapping("/{phoneId}/ordered-services/{orderedServiceId}/terminate")
  public void terminateService(@PathVariable("phoneId") Long phoneId,
      @PathVariable("orderedServiceId") Long orderedServiceId) {
    phoneNumberService.terminateServiceForNumber(phoneId, orderedServiceId);
  }


}
