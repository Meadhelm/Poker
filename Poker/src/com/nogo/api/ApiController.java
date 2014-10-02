package com.nogo.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nogo.api.annotation.Endpoint;

@Controller
public class ApiController {

  @Autowired
  private ApiGenerator generator;

  @Endpoint(name = "FEATURES")
  @RequestMapping(value = "/v1/features", method = RequestMethod.GET,
  produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public List<EndpointDto> getFeatures() {
    return generator.getEndpoints();
  }
}
