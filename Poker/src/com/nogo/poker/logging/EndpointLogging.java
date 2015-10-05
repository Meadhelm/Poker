package com.nogo.poker.logging;

import static java.lang.String.format;
import static java.lang.System.currentTimeMillis;
import static org.apache.commons.lang3.StringUtils.join;

import com.google.gson.Gson;

import com.nogo.api.annotation.Endpoint;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Aspect
public class EndpointLogging {

  private static final Gson gson = new Gson();

  private final ThreadLocal<String> name = new ThreadLocal<>();
  private final ThreadLocal<String> request = new ThreadLocal<>();
  private final ThreadLocal<String> response = new ThreadLocal<>();
  private final ThreadLocal<Long> startTime = new ThreadLocal<>();


  /**
   * An aspect to log whenever an endpoint method is invoked.
   *
   * @param jp joinpoint
   */
  @Before("@annotation(com.nogo.api.annotation.Endpoint)")
  public void beforeLog(final JoinPoint jp) {
    final Method method = this.getMethod(jp);
    final Endpoint endpoint = method.getAnnotation(Endpoint.class);
    name.set(endpoint.name());

    final List<String> reqText = new ArrayList<>();
    for (final Object o : jp.getArgs()) {
      if (o != null) {
        reqText.add(format("%s: %s ", o.getClass().getSimpleName(), gson.toJson(o)));
      }
    }
    request.set(join(reqText, ""));
    startTime.set(currentTimeMillis());
  }

  /**
   * An aspect to log whenever an endpoint method is invoked.
   *
   * @param jp joinpoint
   */
  @AfterReturning(pointcut = "@annotation(com.nogo.api.annotation.Endpoint)", returning = "retVal")
  public void afterLog(final JoinPoint jp, final Object retVal) {
    response.set(retVal.toString());
    final long elapsedTime = System.currentTimeMillis() - startTime.get();

    final Log log = this.getLog(jp);
    log.info(format("[ENDPOINT]=%s [REQ]=%s [RES]=%s [TIME]=%sms", name.get(), request.get(),
        response.get(), elapsedTime));
  }


  protected Log getLog(final JoinPoint jp) {
    Log log = null;
    try {
      final Object test = jp.getTarget();
      log = LogFactory.getLog(test.getClass());
    } finally {
      if (log == null) {
        log = LogFactory.getLog(EndpointLogging.class);
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
