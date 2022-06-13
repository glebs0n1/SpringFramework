package com.ernestas.medus.validators;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.ernestas.medus.error.BadRequestException;
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
  public void givenCodeWithIllegalLength_whenValidate_throwsBadRequest() {
    assertThatThrownBy(() -> lithuanianPersonalCodeValidator.validate("394081561410"))
        .isInstanceOf(BadRequestException.class);
  }

  @Test
  public void givenCodeWithIllegalMonth_whenValidate_throwsBadRequest() {
    assertThatThrownBy(() -> lithuanianPersonalCodeValidator.validate("39413150614"))
        .isInstanceOf(BadRequestException.class);
  }

  @Test
  public void givenCodeWithIllegalDay_whenValidate_throwsBadRequest() {
    assertThatThrownBy(() -> lithuanianPersonalCodeValidator.validate("39408400614"))
        .isInstanceOf(BadRequestException.class);
  }

  @Test
  public void givenCodeWithIllegalFinalNumber_whenValidate_throwsBadRequest() {
    assertThatThrownBy(() -> lithuanianPersonalCodeValidator.validate("39408150615"))
        .isInstanceOf(BadRequestException.class);
  }

  @Test
  public void givenCodeWithIllegalFirstNumber_whenValidate_throwsBadRequest() {
    assertThatThrownBy(() -> lithuanianPersonalCodeValidator.validate("79408150615"))
        .isInstanceOf(BadRequestException.class);
  }

  @Test
  public void finalNumberCheckOK() {
    lithuanianPersonalCodeValidator.validate("68408160103");
  }




}
