package com.ernestas.medus.entities.customer;

import com.ernestas.medus.entities.account.Account;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
@Entity(name = "t_customer")
public class Customer {

  @Id
  @GeneratedValue
  @Column(name = "customer_id")
  private Long id;

  @NotNull @Size(min = 3, max = 15)
  private String name;

  @NotNull @Size(min = 3, max = 20)
  private String surname;

  @NotNull @Size(min = 11, max = 11)
  private String personalCode;

  private String companyName;

  private String companyCode;

  @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
  private List<Account> customerAccounts = new ArrayList<>();

}
