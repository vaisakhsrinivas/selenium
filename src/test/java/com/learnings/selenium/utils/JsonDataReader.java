package com.learnings.selenium.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public final class JsonDataReader {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private JsonDataReader() {
    }

    public static List<Map<String, Object>> readJsonArray(String resourcePath) {
        try (InputStream inputStream = JsonDataReader.class.getClassLoader().getResourceAsStream(resourcePath)) {
            if (inputStream == null) {
                throw new IllegalArgumentException("Resource not found: " + resourcePath);
            }
            return MAPPER.readValue(inputStream, new TypeReference<>() {});
        } catch (Exception e) {
            throw new RuntimeException("Failed to read JSON resource: " + resourcePath, e);
        }
    }

    public static Map<String, Object> readFirstObject(String resourcePath) {
        return readJsonArray(resourcePath).get(0);
    }

    @SuppressWarnings("unchecked")
    public static <T> T get(Map<String, Object> map, String key) {
        return (T) map.get(key);
    }

    public static String getString(Map<String, Object> map, String key) {
        Object value = map.get(key);
        return value == null ? null : value.toString();
    }
}
