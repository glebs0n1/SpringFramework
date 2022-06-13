package com.company.PhoneJava.entities.service;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillableServiceCreate {

  @NotNull
  @Size(min = 6, max = 20)
  private String name;

  @NotNull @Size(min = 20, max = 140)
  private String description;

  @NotNull @Enumerated(value = EnumType.STRING)
  private BillableServiceType billableServiceType;

}
