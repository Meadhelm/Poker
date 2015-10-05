package com.nogo.poker.validation;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;

@Component
public class ValidationService {
  private ValidatorFactory factory;

  @PostConstruct
  private void init() {
    factory = Validation.buildDefaultValidatorFactory();
  }

  /**
   * Validates JSR303 annoation based constraints
   * 
   * @param obect The object to validate
   * @return A string containing validation errors, null if valid.
   */
  public String validate(final Object obect) {
    final Set<ConstraintViolation<Object>> violations = factory.getValidator().validate(obect);
    if (violations.size() == 0) {
      return null;
    }
    final List<String> failureMessage = new ArrayList<>();
    for (final ConstraintViolation<Object> violation : violations) {
      failureMessage.add(violation.getMessage());
    }
    return StringUtils.join(failureMessage, " ,");
  }
}
