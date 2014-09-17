package com.nogo.api;

public class EndpointDto {
  private String type;
  private String api;
  private String uri;
  private String requestBody;
  private String responseBody;
  private String responseStatus;

  public EndpointDto() {

  }

  public String getType() {
    return type;
  }

  public void setType(final String type) {
    this.type = type;
  }

  public String getApi() {
    return api;
  }

  public void setApi(final String api) {
    this.api = api;
  }

  public String getUri() {
    return uri;
  }

  public void setUri(final String uri) {
    this.uri = uri;
  }

  public String getRequestBody() {
    return requestBody;
  }

  public void setRequestBody(final String requestBody) {
    this.requestBody = requestBody;
  }

  public String getResponseBody() {
    return responseBody;
  }

  public void setResponseBody(final String responseBody) {
    this.responseBody = responseBody;
  }

  public String getResponseStatus() {
    return responseStatus;
  }

  public void setResponseStatus(final String responseStatus) {
    this.responseStatus = responseStatus;
  }

}
