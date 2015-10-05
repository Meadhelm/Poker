package com.nogo.api.annotation;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

@Aspect
public class LogDebugInterceptor {

  @Before("@annotation(com.nogo.api.annotation.Endpoint)")
  public void beforeLog(final JoinPoint jp) {
    final Log log = this.getLog(jp);
    if (log.isDebugEnabled()) {
      log.debug(this.getMethod(jp));
      for (final Object o : jp.getArgs()) {
        if (o != null) {
          log.debug("PARAMETER " + o.getClass().getSimpleName());
          log.debug(o.toString());
        }
      }
    }
  }

  @AfterReturning(pointcut = "@annotation(com.nogo.api.annotation.Endpoint)", returning = "retVal")
  public void afterLog(final JoinPoint jp, final Object retVal) {
    final Log log = this.getLog(jp);
    if (log.isDebugEnabled()) {
      log.debug(retVal);
    }
  }


  protected Log getLog(final JoinPoint jp) {
    Log log = null;
    try {
      final Object test = jp.getTarget();
      log = LogFactory.getLog(test.getClass());
    } finally {
      if (log == null) {
        log = LogFactory.getLog(LogDebugInterceptor.class);
      }
    }
    return log;
  }

  @SuppressWarnings({"unchecked", "finally"})
  protected Method getMethod(final JoinPoint jp) {
    Method invoked = null;
    try {
      final MethodSignature met = (MethodSignature) jp.getSignature();
      invoked = jp.getSourceLocation().getWithinType().getMethod(met.getMethod().getName(),
          met.getMethod().getParameterTypes());
    } finally {
      return invoked;
    }
  }
}
