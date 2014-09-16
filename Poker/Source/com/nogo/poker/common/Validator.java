package com.nogo.poker.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public final class Validator {
  private javax.validation.Validator validator;

  @PostConstruct
  private void init() {
    final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  public String validate(final Object obect) {
    final Set<ConstraintViolation<Object>> violations = validator.validate(obect);
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
