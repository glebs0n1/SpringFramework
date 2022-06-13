package com.company.PhoneJava.validators;

import com.company.PhoneJava.error.BadRequestException;

import java.util.List;
import java.util.stream.IntStream;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/*
  Implemented from https://lt.wikipedia.org/wiki/Asmens_kodas
 */

@Primary
@Component
public class LithuanianPersonalCodeValidator implements PersonalCodeValidator {

  private final List<Integer> multipliers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 1);
  private final List<Integer> secondMultipliers = List.of(3, 4, 5, 6, 7, 8, 9, 1, 2, 3);

  @Override
  public void validate(String personalCode) {
    if (personalCode.length() == 11) {
      validateFirstNumber(personalCode.charAt(0));
      validateDayAndMonth(personalCode.substring(3, 5), personalCode.substring(5, 7));
      validateFinalNumber(personalCode);
    } else {
      throw new BadRequestException("Personal code length invalid");
    }
  }

  private void validateFirstNumber(char firstNumber) {
    boolean firstNumberValid = IntStream
        .range(1, 7)
        .anyMatch(value -> value == Character.getNumericValue(firstNumber));

    if (!firstNumberValid) {
      throw new BadRequestException("Invalid first number of personal code");
    }
  }

  private void validateDayAndMonth(String month, String day) {
    int dayInteger = Integer.parseInt(day);
    int monthInteger = Integer.parseInt(month);

    if (!(monthInteger <= 12 && monthInteger >= 0)) {
      throw new BadRequestException("Invalid month in personal code");
    }

    if (!(dayInteger <= 31 && dayInteger >= 0)) {
      throw new BadRequestException("Invalid day in personal code");
    }
  }

  private void validateFinalNumber(String personalCode) {

    int lastNumber = Character.getNumericValue(personalCode.charAt(personalCode.length() - 1));

    char[] array = new char[personalCode.length() - 1];

    personalCode.getChars(0, personalCode.length() - 1, array, 0);

    int sum = getSum(array, multipliers);

    if (sum % 11 == 10) {
      sum = getSum(array, secondMultipliers);
      int finalNumber = sum % 11 == 10 ? 0 : sum % 11;
      if (finalNumber != lastNumber) {
        throw new BadRequestException("Personal code final number invalid");
      }
    } else if (sum % 11 != lastNumber) {
      throw new BadRequestException("Personal code final number invalid");
    }

  }


  private Integer getSum(char[] array, List<Integer> multipliers) {
    return IntStream.range(0, 10).map(integer ->
        Character.getNumericValue(array[integer]) * multipliers.get(integer)).sum();
  }

}
