package com.nuan.autoconfigure.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * @program: learnspringboot
 * @description:
 * @author: yeshengnuan
 * @create: 2021-03-05 12:30
 **/
public class SqlDateSerializer extends JsonSerializer<Date>
{

    public SqlDateSerializer(){}

    @Override
    public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String sqlDateSerializer=sdf.format(date);
        jsonGenerator.writeString(sqlDateSerializer);
    }

}
