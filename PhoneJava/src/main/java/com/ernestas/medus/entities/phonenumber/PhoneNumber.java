package com.ernestas.medus.entities.phonenumber;


import com.ernestas.medus.entities.account.Account;
import com.ernestas.medus.entities.orderedservice.OrderedService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "t_phone_number")
public class PhoneNumber {

  @Id
  @GeneratedValue
  @Column(name = "phone_number_id")
  private Long id;

  @NotNull
  private LocalDateTime activeFrom;

  @NotNull
  private LocalDateTime activeTo;

  @NotNull
  private String phoneDigits;

  @ManyToOne
  @JoinColumn(name="account_id", nullable=false)
  @JsonIgnore
  private Account account;

  @OneToMany(mappedBy = "phoneNumber", cascade = CascadeType.ALL)
  private List<OrderedService> orderedServiceList = new ArrayList<>();

}
