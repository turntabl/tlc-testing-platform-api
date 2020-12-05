package com.turntabl.testsystem.helper;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.springframework.core.convert.converter.Converter;

import java.util.UUID;

public class StringToUserIdConverter implements Converter<String, UUID> {
    @Override
    public UUID convert(String source) {
        return UUID.fromString(source);
    }

    @Override
    public <U> Converter<String, U> andThen(Converter<? super UUID, ? extends U> after) {
        return null;
    }
}
