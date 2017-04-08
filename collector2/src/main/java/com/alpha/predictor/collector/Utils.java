package com.alpha.predictor.collector;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

/**
 * Created by dfs1 on 08-04-17.
 */
public class Utils {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static String serializeToString(Map<String, String> map) throws JsonProcessingException {
        return OBJECT_MAPPER.writeValueAsString(map);
    }
}
