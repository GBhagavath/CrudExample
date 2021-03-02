package com.example.practice.common;

import com.example.practice.model.Employee;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class APIResponseBuilder {
    public static final String RESPONSE_CODE = "responseCode";
    private static final String RESPONSE_MESSAGE = "responseMessage";
    private static final String USER_ID = "userId";
    private static final String USER_DETAILS = "user";

    public Map<String, Object> buildResponse(String id, String responseCode, String message) {
        Map<String, Object> map = new HashMap<>();
        map.put(RESPONSE_CODE, responseCode);
        map.put(RESPONSE_MESSAGE, message);
        map.put(USER_ID, id);
        return map;
    }

    public Map<String, Object> buildResponse(String id, Employee user, String responseCode) {
        Map<String, Object> map = new HashMap<>();
        map.put(RESPONSE_CODE, responseCode);
        map.put(USER_DETAILS, user);
        map.put(USER_ID, id);
        return map;
    }

    public Map<String, Object> buildResponse(String responseCode, String message) {
        Map<String, Object> map = new HashMap<>();
        map.put(RESPONSE_CODE, responseCode);
        map.put(RESPONSE_MESSAGE, message);
        return map;
    }
}
