package com.nogo.poker.time;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import java.io.IOException;

public class DateTimeDeserializer extends JsonDeserializer<DateTime> {

  private static DateTimeFormatter formatter = ISODateTimeFormat.dateTime();

  @Override
  public DateTime deserialize(final JsonParser parser, final DeserializationContext ctxt)
      throws IOException, JsonProcessingException {
    return formatter.parseDateTime(parser.getValueAsString());
  }

}
