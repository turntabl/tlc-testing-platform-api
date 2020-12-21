package com.turntabl.testsystem.helper;

import com.turntabl.testsystem.model.QuestionType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class QuestionTypeConverter implements AttributeConverter<QuestionType, String> {
    @Override
    public String convertToDatabaseColumn(QuestionType questionType){
        if(questionType == null){
            return null;
        }
        return questionType.getCode();
    }

    @Override
    public QuestionType convertToEntityAttribute(String code){
        if(code == null){
            return null;
        }
        return Stream.of(QuestionType.values())
                .filter( questionType ->
                    questionType.getCode().equals(code)
                ).findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
