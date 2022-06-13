package com.ernestas.medus.entities.account;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountUpdate {

  @NotNull
  @Size(min = 10, max = 20)
  private String name;

  @NotNull
  @Size(min = 16, max = 120)
  private String description;

}



