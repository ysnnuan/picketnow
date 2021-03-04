package com.nuan.autoconfigure.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.util.Date;

public class DateDeserializer extends StdDeserializer<Date>
{

    public DateDeserializer(Class<?> vc)
    {
        super(vc);
    }

    public DateDeserializer(JavaType valueType)
    {
        super(valueType);
    }

    public DateDeserializer(StdDeserializer<?> src)
    {
        super(src);
    }

    @Override
    public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException
    {
        return DatePatternUtil.getPatternDate(jsonParser.getValueAsString());
    }
}
