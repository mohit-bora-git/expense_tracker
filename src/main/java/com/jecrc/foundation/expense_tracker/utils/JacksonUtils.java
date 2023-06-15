package com.jecrc.foundation.expense_tracker.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


public class JacksonUtils {

    private static final Logger logger = LoggerFactory.getLogger(JacksonUtils.class);

    private static ObjectMapper objectMapper = new ObjectMapper();

    private JacksonUtils() {
    }

    public static ObjectMapper getObjectMapper() {
        return objectMapper;
    }


    public static String toJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            logger.error("Exception while converting Object to JSON due to {}",
                StringUtils.printStackTrace(e));
            return null;
        }
    }

    public static <T> T fromJson(String json, Class<T> cls) {
        try {
            return objectMapper.readValue(json, cls);
        } catch (Exception e) {
            logger.error("Exception while converting JSON to Object JSON: {} due to {}", json,
                StringUtils.printStackTrace(e));
            return null;
        }
    }

    public static <T> T fromJson(String json, TypeReference<T> typeRef) {
        try {
            return objectMapper.readValue(json, typeRef);
        } catch (Exception e) {
            logger.error("Exception while converting JSON to Object JSON: {} due to {}", json,
                StringUtils.printStackTrace(e));
            return null;
        }
    }

    public static <T> List<T> fromJsonToList(String json, Class<T> cls) {
        try {
            return objectMapper.readValue(json, new TypeReference<List<T>>() {
            });
        } catch (Exception e) {
            logger.info(
                "Exception while converting JSON Array to List of Objects JSON: {} due to {}", json,
                StringUtils.printStackTrace(e));
            return new ArrayList<>();
        }
    }
}
