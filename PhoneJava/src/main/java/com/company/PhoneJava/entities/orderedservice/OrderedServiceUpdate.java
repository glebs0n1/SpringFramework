package com.company.PhoneJava.entities.orderedservice;

import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderedServiceUpdate {

  @NotNull
  @Size(min = 6, max = 20)
  private String name;

  @NotNull
  private LocalDateTime activeFrom;

  @NotNull
  private LocalDateTime activeTo;

}