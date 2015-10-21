package com.nogo.poker.web;

import com.nogo.poker.domain.Pagination;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class PaginationResolver implements HandlerMethodArgumentResolver {

  private static final String BEGIN_INDEX = "start";
  private static final String END_INDEX = "stop";

  @Override
  public boolean supportsParameter(final MethodParameter parameter) {
    if (Pagination.class.equals(parameter.getParameterType())) {
      return true;
    }
    return false;
  }

  @Override
  public Object resolveArgument(final MethodParameter parameter,
      final ModelAndViewContainer mavContainer, final NativeWebRequest webRequest,
      final WebDataBinderFactory binderFactory) throws Exception {

    return new Pagination(webRequest.getParameter(BEGIN_INDEX), webRequest.getParameter(END_INDEX));
  }

}
