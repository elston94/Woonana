package com.ssafy.woonana.domain.model.entity.board;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class StatusAttributeConverter implements AttributeConverter<String, Integer> {

    @Override
    public Integer convertToDatabaseColumn(String attribute) {
        if ("CLOSE".equals(attribute)) return 1;
        else if ("DONE".equals(attribute)) return 2;
        return 0;
    }

    @Override
    public String convertToEntityAttribute(Integer dbData) {
        if (1 == dbData) return "CLOSE";
        else if (2 == dbData) return "DONE";
        return "OPEN";
    }
}
