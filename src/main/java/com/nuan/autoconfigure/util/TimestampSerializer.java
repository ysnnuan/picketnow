package com.nuan.autoconfigure.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * @program: learnspringboot
 * @description:
 * @author: yeshengnuan
 * @create: 2021-03-05 12:30
 **/
public class TimestampSerializer extends JsonSerializer<Timestamp>
{

    public TimestampSerializer(){}

    @Override
    public void serialize(Timestamp timestamp, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String timestampSerializer=sdf.format(timestamp);
        jsonGenerator.writeString(timestampSerializer);
    }

}
