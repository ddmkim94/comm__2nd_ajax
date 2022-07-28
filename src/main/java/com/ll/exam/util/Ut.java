package com.ll.exam.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.LinkedHashMap;
import java.util.Map;

public class Ut {
    public static Map<String, Object> mapOf(Object... data) {
        Map<String, Object> map = new LinkedHashMap<>();

        int dataSize = data.length / 2;
        for (int i = 0; i < dataSize; i++) {
            int keyIndex = i * 2;
            int valueIndex = i * 2 + 1;

            String key = (String) data[keyIndex];
            Object value = data[valueIndex];

            map.put(key, value);
        }
        return map;
    }

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

        //<T>는 해당 메서드가 제네릭 메서드임을 나타내는 거고, T는 리턴타입을 말하는것!
        public static <T> T toObj(String json, Class<T> clazz, T defaultValue) {
            try {
                return om.readValue(json, clazz);
            } catch (JsonProcessingException e) {
                return null;
            }
        }

        public static <T> T toObj(String json, TypeReference<T> typeReference, T defaultValue) {
            try {
                return om.readValue(json, typeReference);
            } catch (JsonProcessingException e) {
                return null;
            }
        }
    }
}
