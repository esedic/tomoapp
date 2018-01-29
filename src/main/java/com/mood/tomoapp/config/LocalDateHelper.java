package com.mood.tomoapp.config;

import static java.time.temporal.ChronoField.DAY_OF_MONTH;
import static java.time.temporal.ChronoField.MONTH_OF_YEAR;
import static java.time.temporal.ChronoField.YEAR;

import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.SignStyle;

import javax.persistence.AttributeConverter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * @author <a href="mailto:ales.justin@jboss.org">Ales Justin</a>
 */
public class LocalDateHelper extends PropertyEditorSupport implements AttributeConverter<LocalDate, Date> {
    private static final DateTimeFormatter FORMATTER = new DateTimeFormatterBuilder()
        .appendValue(DAY_OF_MONTH, 1, 2, SignStyle.NEVER)
        .appendLiteral('.')
        .appendValue(MONTH_OF_YEAR, 1, 2, SignStyle.NEVER)
        .appendLiteral('.')
        .appendValue(YEAR, 4)
        .toFormatter();

    private static LocalDate parse(String text) {
        return LocalDate.parse(text, FORMATTER);
    }

    private static String format(LocalDate date) {
        return date.format(FORMATTER);
    }

    @Override
    public Date convertToDatabaseColumn(LocalDate locDate) {
        return (locDate == null ? null : Date.valueOf(locDate));
    }

    @Override
    public LocalDate convertToEntityAttribute(Date sqlDate) {
        return (sqlDate == null ? null : sqlDate.toLocalDate());
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (text != null) {
            setValue(parse(text));
        }
    }

    @Override
    public String getAsText() {
        if (getValue() == null) {
            return null;
        }
        return format(LocalDate.class.cast(getValue()));
    }

    public static class Serializer extends JsonSerializer<LocalDate> {
        @Override
        public void serialize(LocalDate value, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
            gen.writeString(format(value));
        }
    }

    public static class Deserializer extends JsonDeserializer<LocalDate> {
        @Override
        public LocalDate deserialize(JsonParser jp, DeserializationContext deserializationContext) throws IOException {
            return parse(jp.readValueAs(String.class));
        }
    }
}
