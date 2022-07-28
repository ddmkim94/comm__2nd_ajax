package com.ll.exam.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Ut {
    public static class json {

        public static String toJsonStr(Object object, String defaultValue) {
            ObjectMapper om = new ObjectMapper();
            try {
                return om.writeValueAsString(object);
            } catch (JsonProcessingException e) {
                return defaultValue;
            }
        }
    }
}
