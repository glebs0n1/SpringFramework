package com.ernestas.medus.entities.orderedservice;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderedServiceRepository extends JpaRepository<OrderedService, Long> {

  public List<OrderedService> findAllByBillableService_Id(Long id);

}
