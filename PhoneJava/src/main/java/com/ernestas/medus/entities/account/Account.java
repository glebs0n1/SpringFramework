package com.ernestas.medus.entities.account;

import com.ernestas.medus.entities.customer.Customer;
import com.ernestas.medus.entities.phonenumber.PhoneNumber;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "t_account")
public class Account {

  @Id
  @GeneratedValue
  @Column(name = "account_id")
  private Long id;

  @ManyToOne
  @JoinColumn(name="customer_id", nullable=false)
  @JsonIgnore
  private Customer customer;

  @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
  private List<PhoneNumber> phoneNumbers = new ArrayList<>();

  @NotNull @Size(min = 10, max = 20)
  private String name;

  @NotNull @Size(min = 16, max = 120)
  private String description;

}
