package com.mood.tomoapp.config;

import java.io.IOException;
import java.time.LocalDateTime;
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
public class LocalDateTimeHelper {
    public static class Serializer extends JsonSerializer<LocalDateTime> {
        @Override
        public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
            gen.writeString(value.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        }
    }

    public static class Deserializer extends JsonDeserializer<LocalDateTime> {
        @Override
        public LocalDateTime deserialize(JsonParser jp, DeserializationContext deserializationContext) throws IOException {
            return LocalDateTime.parse(jp.readValueAs(String.class));
        }
    }
}
