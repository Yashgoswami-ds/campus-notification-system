package com.campus.logging.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

    public class JsonUtil {
        private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private  JsonUtil(){
        throw  new IllegalStateException("utility exception");
    }

    public static  String toJson(Object object){
        if (object==null){
            return "";
        }
        try {
            return OBJECT_MAPPER.writeValueAsString(object);
        }catch (JsonProcessingException exception){
            return  object.toString();

        }
    }
    }
