package com.nuan.autoconfigure.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * @program: learnspringboot
 * @description:
 * @author: yeshengnuan
 * @create: 2021-03-05 12:30
 **/
public class SqlDateDeserializer extends JsonDeserializer<Date>
{

    public SqlDateDeserializer(){}
    @Override
    public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException
    {
        String text = jsonParser.getText().trim();
        Date date=Date.valueOf(text);
        return date;
    }
}
