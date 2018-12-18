package com.udriving.drivingapi.util;


import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by IntelliJ IDEA
 * Coder : haiyang
 * Date:2018/12/3
 */
public class JacksonUtil {
    private static ObjectMapper mapper = new ObjectMapper();

    public static String toJSONString(Object obj) {
        StringWriter sw = new StringWriter();
        JsonGenerator gen = null;
        try {
            gen = new JsonFactory().createJsonGenerator(sw);
            mapper.writeValue(gen, obj);
            gen.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sw.toString();
    }

    public static ObjectMapper getMapper() {
        return mapper;
    }

    public static <T> T json2Bean(String jsonStr, Class<T> objClass)
            throws IOException {
        return mapper.readValue(jsonStr, objClass);
    }


    public static Object toJSON(String[] roles) {
        return null;
    }
}