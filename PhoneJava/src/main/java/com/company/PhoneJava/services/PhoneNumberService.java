package com.company.PhoneJava.services;

import com.company.PhoneJava.entities.orderedservice.OrderedService;
import com.company.PhoneJava.entities.orderedservice.OrderedServiceCreate;
import com.company.PhoneJava.entities.orderedservice.OrderedServiceRepository;
import com.company.PhoneJava.entities.orderedservice.OrderedServiceUpdate;
import com.company.PhoneJava.entities.phonenumber.PhoneNumber;
import com.company.PhoneJava.entities.phonenumber.PhoneNumberRepository;
import com.company.PhoneJava.entities.service.BillableService;
import com.company.PhoneJava.entities.service.BillableServiceRepository;
import com.company.PhoneJava.error.BadRequestException;
import com.company.PhoneJava.error.NotFoundException;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhoneNumberService {

  private PhoneNumberRepository phoneNumberRepository;

  private BillableServiceRepository billableServiceRepository;

  private OrderedServiceRepository orderedServiceRepository;

  @Autowired
  public PhoneNumberService(PhoneNumberRepository phoneNumberRepository,
      BillableServiceRepository billableServiceRepository,
      OrderedServiceRepository orderedServiceRepository) {
    this.phoneNumberRepository = phoneNumberRepository;
    this.billableServiceRepository = billableServiceRepository;
    this.orderedServiceRepository = orderedServiceRepository;
  }

  @Transactional
  public void orderServiceForNumber(Long phoneNumberId, Long serviceId,
      OrderedServiceCreate orderedServiceCreate) {

    PhoneNumber phoneNumber = phoneNumberRepository.findById(phoneNumberId).orElseThrow(
        () -> new NotFoundException("Phone number not found"));

    BillableService billableService = billableServiceRepository.findById(serviceId)
        .orElseThrow(() ->
            new NotFoundException("Service not found"));

    if (orderedServiceCreate.getActiveFrom().isAfter(orderedServiceCreate.getActiveTo())) {
      throw new BadRequestException("Service active from date cannot be after active to date");
    }

    if (orderedServiceCreate.getActiveFrom().equals(orderedServiceCreate.getActiveTo())) {
      throw new BadRequestException("Service order dates cannot be equal");
    }

    OrderedService orderedService = OrderedService.builder()
        .activeFrom(orderedServiceCreate.getActiveFrom())
        .activeTo(orderedServiceCreate.getActiveTo())
        .billableService(billableService)
        .phoneNumber(phoneNumber)
        .name(orderedServiceCreate.getName())
        .build();

    phoneNumber.getOrderedServiceList().add(orderedService);

    phoneNumberRepository.saveAndFlush(phoneNumber);
  }

  public void terminateServiceForNumber(Long phoneNumberId, Long orderedServiceId) {

    PhoneNumber phoneNumber = phoneNumberRepository.findById(phoneNumberId).orElseThrow(
        () -> new NotFoundException("Phone number not found"));

    OrderedService orderedService = orderedServiceRepository.findById(orderedServiceId).orElseThrow(
        () -> new NotFoundException("Ordered service not found"));

    phoneNumber.getOrderedServiceList().remove(orderedService);

    phoneNumberRepository.saveAndFlush(phoneNumber);
  }

  public void updateServiceForNumber(Long phoneNumberId, Long orderedServiceId,
      OrderedServiceUpdate orderedServiceUpdate) {

    PhoneNumber phoneNumber = phoneNumberRepository.findById(phoneNumberId).orElseThrow(
        () -> new NotFoundException("Phone number not found"));

    OrderedService orderedService = orderedServiceRepository.findById(orderedServiceId).orElseThrow(
        () -> new NotFoundException("Ordered service not found"));

    if (orderedServiceUpdate.getActiveFrom().isAfter(orderedServiceUpdate.getActiveTo())) {
      throw new BadRequestException("Service active from date cannot be after active to date");
    }

    if (orderedServiceUpdate.getActiveFrom().equals(orderedServiceUpdate.getActiveTo())) {
      throw new BadRequestException("Service order dates cannot be equal");
    }

    orderedService.setActiveTo(orderedServiceUpdate.getActiveTo());
    orderedService.setActiveFrom(orderedServiceUpdate.getActiveFrom());
    orderedService.setName(orderedServiceUpdate.getName());

    orderedServiceRepository.saveAndFlush(orderedService);
  }


  public PhoneNumber getPhoneNumberById(Long phoneNumberId) {
    return phoneNumberRepository.findById(phoneNumberId).orElseThrow(
        () -> new NotFoundException("Phone number not found")
    );
  }

  public List<PhoneNumber> getPhoneNumbers() {
    return phoneNumberRepository.findAll();
  }

}
