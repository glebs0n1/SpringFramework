package com.company.PhoneJava.validators;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.company.PhoneJava.error.BadRequestException;
import org.junit.Before;
import org.junit.Test;

public class PersonalCodeValidatorTests {

  private LithuanianPersonalCodeValidator lithuanianPersonalCodeValidator;

  @Before
  public void setup() {
    lithuanianPersonalCodeValidator = new LithuanianPersonalCodeValidator();
  }


  @Test
  public void validateOk() {
    lithuanianPersonalCodeValidator.validate("39408150614");
  }


  @Test
  public void finalNumberCheckOK() {
    lithuanianPersonalCodeValidator.validate("68408160103");
  }

}
