package com.company.PhoneJava.entities.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillableServiceRepository extends JpaRepository<BillableService, Long> { }
