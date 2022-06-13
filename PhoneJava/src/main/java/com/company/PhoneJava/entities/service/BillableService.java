package com.company.PhoneJava.entities.service;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity(name = "t_billable_service")
public class BillableService {

  @Id
  @GeneratedValue
  @Column(name = "serviceId")
  private Long id;

  @NotNull @Size(min = 6, max = 20)
  private String name;

  @NotNull @Size(min = 20, max = 140)
  private String description;

  @NotNull @Enumerated(value = EnumType.STRING)
  private BillableServiceType billableServiceType;

}
