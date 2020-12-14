package com.turntabl.testsystem.helper;
import org.springframework.core.convert.converter.Converter;

import java.util.UUID;
public class StringToUserIdConverter implements Converter<String, UUID> {
    public StringToUserIdConverter() {
    }
    @Override
    public UUID convert(String source) {
        return UUID.fromString(source);
    }
    @Override
    public <U> Converter<String, U> andThen(Converter<? super UUID, ? extends U> after) {
        return null;
    }
}
