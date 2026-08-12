package com.learnings.selenium.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public final class AuthSessionManager {

    private static final ObjectMapper MAPPER = new ObjectMapper();
    public static final String DEFAULT_AUTH_FILE = ".auth/smoketest.json";

    private AuthSessionManager() {
    }

    public static void saveCookies(WebDriver driver, String filePath) {
        try {
            Path path = Path.of(filePath);
            Files.createDirectories(path.getParent());
            Set<Cookie> cookies = driver.manage().getCookies();
            Map<String, Object> payload = new HashMap<>();
            payload.put("cookies", cookies.stream().map(AuthSessionManager::cookieToMap).toList());
            MAPPER.writerWithDefaultPrettyPrinter().writeValue(new File(filePath), payload);
        } catch (IOException e) {
            throw new RuntimeException("Failed to save auth session to " + filePath, e);
        }
    }

    public static void loadCookies(WebDriver driver, String baseUrl, String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new IllegalStateException("Auth session file not found: " + filePath);
        }

        try {
            Map<String, Object> payload = MAPPER.readValue(file, new TypeReference<>() {});
            @SuppressWarnings("unchecked")
            Iterable<Map<String, Object>> cookies = (Iterable<Map<String, Object>>) payload.get("cookies");

            driver.get(baseUrl);
            driver.manage().deleteAllCookies();

            for (Map<String, Object> cookieMap : cookies) {
                Cookie.Builder builder = new Cookie.Builder(
                        cookieMap.get("name").toString(),
                        cookieMap.get("value").toString()
                );
                if (cookieMap.get("domain") != null) {
                    builder.domain(cookieMap.get("domain").toString());
                }
                if (cookieMap.get("path") != null) {
                    builder.path(cookieMap.get("path").toString());
                }
                if (cookieMap.get("expiry") != null) {
                    Number expiry = (Number) cookieMap.get("expiry");
                    builder.expiresOn(new java.util.Date(expiry.longValue() * 1000L));
                }
                if (Boolean.TRUE.equals(cookieMap.get("secure"))) {
                    builder.isSecure(true);
                }
                if (Boolean.TRUE.equals(cookieMap.get("httpOnly"))) {
                    builder.isHttpOnly(true);
                }
                driver.manage().addCookie(builder.build());
            }

            driver.navigate().refresh();
        } catch (IOException e) {
            throw new RuntimeException("Failed to load auth session from " + filePath, e);
        }
    }

    private static Map<String, Object> cookieToMap(Cookie cookie) {
        Map<String, Object> map = new HashMap<>();
        map.put("name", cookie.getName());
        map.put("value", cookie.getValue());
        map.put("domain", cookie.getDomain());
        map.put("path", cookie.getPath());
        if (cookie.getExpiry() != null) {
            map.put("expiry", cookie.getExpiry().getTime() / 1000L);
        }
        map.put("secure", cookie.isSecure());
        map.put("httpOnly", cookie.isHttpOnly());
        return map;
    }
}
