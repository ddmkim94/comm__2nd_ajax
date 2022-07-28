package com.ll.exam.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ll.exam.article.dto.ArticleDto;

import java.util.List;
import java.util.Map;

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
