package com.nogo.api.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD})
public @interface Endpoint {
  /**
   * The name of the endpoint.
   */
  String name() default "";

  /**
   * Exclude specific request body fields.
   */
  String[] excludeRequestBodyFields() default {};

  /**
   * Exclude specific response body fields.
   */
  String[] excludeResponseBodyFields() default {};

  /**
   * Include specific request body fields.
   */
  String[] includeRequestBodyFields() default {};

  /**
   * Include specific response body fields.
   */
  String[] includeResponseBodyFields() default {};
}
