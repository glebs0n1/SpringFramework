package com.company.PhoneJava.entities.phonenumber;


import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhoneNumberCreate {

  @NotNull
  private LocalDateTime activeFrom;

  @NotNull
  private LocalDateTime activeTo;

  @NotNull
  private String phoneDigits;

}
