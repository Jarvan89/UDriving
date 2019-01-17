package com.udriving.drivingapi.util;


import java.io.*;
import java.util.List;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by IntelliJ IDEA
 * Coder : haiyang
 * Date:2018/12/3
 */
public class JacksonUtil {
    private static ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    }


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


    public static <T> T input2Bean(InputStream in, Class<T> objClass) throws IOException {

        //读取 json 数据
        StringBuilder stringBuilder = new StringBuilder();

        if (in != null) {
            //java 7 新特性 自动资源释放
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in))) {
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else {
            stringBuilder.append("");
        }
        return json2Bean(stringBuilder.toString(), objClass);

    }


    public static Object toJSON(String[] roles) {
        return null;
    }
}