package com.ernestas.medus.entities.orderedservice;

import com.ernestas.medus.entities.phonenumber.PhoneNumber;
import com.ernestas.medus.entities.service.BillableService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "t_ordered_service")
public class OrderedService {

  @Id
  @GeneratedValue
  private Long id;

  @ManyToOne
  @JoinColumn(name = "phone_number_id")
  @JsonIgnore
  private PhoneNumber phoneNumber;

  @NotNull @Size(min = 6, max = 20)
  private String name;

  @NotNull
  private LocalDateTime activeFrom;

  @NotNull
  private LocalDateTime activeTo;

  @ManyToOne
  private BillableService billableService;


}
