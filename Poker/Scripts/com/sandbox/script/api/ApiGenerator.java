package com.sandbox.script.api;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.google.gson.Gson;
import com.nogo.api.annotation.Document;
import com.nogo.api.annotation.Endpoint;

public class ApiGenerator {

  public static void main(final String[] args) throws FileNotFoundException,
  UnsupportedEncodingException {
    System.out.println("\nGenerating API\n");

    final String location = args[0];

    final ClassScanner scanner = new ClassScanner();

    final List<EndpointDto> dtos = new ArrayList<>();
    for (final Class<?> clazz : scanner.findClasses()) {
      final Document document = clazz.getAnnotation(Document.class);

      if (document != null) {
        for (final Method method : clazz.getMethods()) {

          final RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);

          if (requestMapping != null) {

            final EndpointDto endpointDto = generateEndpointDto(method, requestMapping);
            dtos.add(endpointDto);
          }
        }
      }
    }
    final PrintWriter writer = new PrintWriter(location + "\\endpoints.json", "UTF-8");
    writer.println(new Gson().toJson(dtos));
    writer.close();
  }

  private static EndpointDto generateEndpointDto(final Method method,
      final RequestMapping requestMapping) {
    final EndpointDto endpoint = new EndpointDto();
    endpoint.setType(requestMapping.method()[0].name());
    endpoint.setUri(String.format("%s", requestMapping.value()[0].toString()));

    final ResponseStatus responseStatus = method.getAnnotation(ResponseStatus.class);
    if (responseStatus != null) {
      endpoint.setResponseStatus(String.format("%d %s", responseStatus.value()
          .value(), responseStatus.value()
          .getReasonPhrase()));
    }

    final Endpoint apiMethod = method.getAnnotation(Endpoint.class);
    if (apiMethod != null) {
      endpoint.setApi(apiMethod.name());
    }

    endpoint.setResponseBody(traceObject(method.getGenericReturnType()));

    int index = 0;
    for (final Annotation[] annotations : method.getParameterAnnotations()) {
      for (final Annotation annotation : annotations) {
        if (RequestBody.class.equals(annotation.annotationType())) {
          endpoint.setRequestBody(traceObject(method.getGenericParameterTypes()[index]));
        }
      }
      index++;
    }

    return endpoint;
  }

  private static String traceObject(final Type type) {
    String responseBody = null;

    if ((type instanceof ParameterizedType) && (type != null)) {
      responseBody =
          "[" + traceObject(((ParameterizedType) type).getActualTypeArguments()[0]) + "]";
    } else {
      @SuppressWarnings("rawtypes")
      final Class classType = (Class) type;

      if (classType.equals(String.class)) {
        responseBody = "String";
      } else {

        for (final Field field : classType.getDeclaredFields()) {
          final Class<?> clazz = field.getType();
          String fieldType = "";

          if (String.class.equals(clazz)) {
            fieldType = "\"String\"";
          } else if (int.class.equals(clazz) || Integer.class.equals(clazz)) {
            fieldType = "\"Integer\"";
          } else if (boolean.class.equals(clazz) || Boolean.class.equals(clazz)) {
            fieldType = "\"Boolean\"";
          } else {
            fieldType = traceObject(field.getType());
          }

          if (responseBody == null) {
            responseBody = "{\"" + field.getName() + "\":" + fieldType;
          } else {

            responseBody = responseBody + ",\"" + field.getName() + "\":" + fieldType;
          }
        }
        if (responseBody != null) {
          responseBody = responseBody + "}";
        }
      }
    }

    return responseBody;
  }
}
