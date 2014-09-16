package com.nogo.poker.web;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class RequestContextResolver implements HandlerMethodArgumentResolver {

  @Override
  public boolean supportsParameter(final MethodParameter parameter) {
    if (RequestContext.class.equals(parameter.getParameterType())) {
      return true;
    }
    return false;
  }

  @Override
  public Object resolveArgument(final MethodParameter parameter,
      final ModelAndViewContainer mavContainer, final NativeWebRequest webRequest,
      final WebDataBinderFactory binderFactory) throws Exception {
    final RequestContext context = new RequestContext();
    context.setName(webRequest.getHeader("test"));
    return context;
  }

}
