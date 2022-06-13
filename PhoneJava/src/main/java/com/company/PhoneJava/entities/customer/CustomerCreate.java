package com.company.PhoneJava.entities.customer;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerCreate {

  @NotNull
  @Size(min = 3, max = 15)
  private String name;

  @NotNull @Size(min = 3, max = 20)
  private String surname;

  @NotNull @Size(min = 11, max = 11)
  private String personalCode;

  private String companyName;

  private String companyCode;

}
