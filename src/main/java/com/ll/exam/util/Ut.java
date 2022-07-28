package com.ll.exam.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Ut {
    public static class json {

        private static final ObjectMapper om;

        static {
            om = new ObjectMapper();
        }

        public static String toStr(Object object, String defaultValue) {
            try {
                return om.writeValueAsString(object);
            } catch (JsonProcessingException e) {
                return defaultValue;
            }
        }

        public static Object toObj(String json, Class clazz, Object defaultValue) {
            try {
                return om.readValue(json, clazz);
            } catch (JsonProcessingException e) {
                return null;
            }
        }
    }
}
