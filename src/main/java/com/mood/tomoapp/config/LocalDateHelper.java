package com.mood.tomoapp.config;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * @author <a href="mailto:ales.justin@jboss.org">Ales Justin</a>
 */
public class LocalDateHelper {
    public static class Serializer extends JsonSerializer<LocalDate> {
        @Override
        public void serialize(LocalDate value, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
            gen.writeString(value.format(DateTimeFormatter.ISO_LOCAL_DATE));
        }
    }

    public static class Deserializer extends JsonDeserializer<LocalDate> {
        @Override
        public LocalDate deserialize(JsonParser jp, DeserializationContext deserializationContext) throws IOException {
            return LocalDate.parse(jp.readValueAs(String.class));
        }
    }
}
