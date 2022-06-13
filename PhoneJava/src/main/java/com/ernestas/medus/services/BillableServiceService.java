package com.ernestas.medus.services;

import com.ernestas.medus.entities.orderedservice.OrderedServiceRepository;
import com.ernestas.medus.entities.service.BillableService;
import com.ernestas.medus.entities.service.BillableServiceCreate;
import com.ernestas.medus.entities.service.BillableServiceRepository;
import com.ernestas.medus.entities.service.BillableServiceUpdate;
import com.ernestas.medus.error.BadRequestException;
import com.ernestas.medus.error.NotFoundException;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillableServiceService {

  private BillableServiceRepository billableServiceRepository;

  private OrderedServiceRepository orderedServiceRepository;

  @Autowired
  public BillableServiceService(BillableServiceRepository billableServiceRepository,
      OrderedServiceRepository orderedServiceRepository) {
    this.billableServiceRepository = billableServiceRepository;
    this.orderedServiceRepository = orderedServiceRepository;
  }

  public List<BillableService> getAllBillableServices() {
    return billableServiceRepository.findAll();
  }

  @Transactional
  public void createBillableService(BillableServiceCreate billableServiceCreate) {
    billableServiceRepository.saveAndFlush(
        BillableService.builder()
            .name(billableServiceCreate.getName())
            .billableServiceType(billableServiceCreate.getBillableServiceType())
            .description(billableServiceCreate.getDescription())
            .build()
    );
  }

  public void updateBillableService(Long serviceId, BillableServiceUpdate billableServiceUpdate) {

    BillableService currentService = billableServiceRepository.findById(serviceId)
        .orElseThrow(() -> new NotFoundException("Billable service not found"));

    if (orderedServiceRepository.findAllByBillableService_Id(serviceId).isEmpty()) {
      currentService.setName(billableServiceUpdate.getName());
      currentService.setDescription(billableServiceUpdate.getDescription());
      currentService.setBillableServiceType(billableServiceUpdate.getBillableServiceType());
      billableServiceRepository.saveAndFlush(currentService);
    } else {
      throw new BadRequestException("Service cannot be updated if it is currently ordered");
    }
  }


  public void deleteBillableServiceById(Long serviceId) {
    if (orderedServiceRepository.findAllByBillableService_Id(serviceId).isEmpty()) {
      billableServiceRepository.deleteById(serviceId);
    } else {
      throw new BadRequestException("Service cannot be deleted if it is currently ordered");
    }

  }

  public BillableService getBillableServiceById(Long id) {
    return billableServiceRepository.findById(id).orElseThrow(() ->
        new NotFoundException("Billable service not found"));
  }


}
