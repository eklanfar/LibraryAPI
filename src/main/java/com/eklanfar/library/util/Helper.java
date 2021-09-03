package com.eklanfar.library.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

public class Helper {

    private static final Logger log = LoggerFactory.getLogger(Helper.class);

    private Helper() {
        throw new IllegalStateException("Cannot create instance of static util class");
    }

    /**
     * Returns JSON string from object or null in case of an error
     *
     * @param obj object to map to JSON
     * @return string from object or null if error
     */
    public static String asJson(final Object obj) {
        try {
            return new JsonMapper().rebuild().addModule(new JavaTimeModule()).build().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.info("FAILED - asJson; {}", e.getMessage());
            log.error("EXCEPTION - asJson", e);
            return null;
        }
    }

    /**
     * Returns Object from map or null in case of an error
     *
     * @param obj   map to be mapped
     * @param clazz class of mapping object
     * @return mapped object or null if error
     */
    public static Object fromMapToObject(final Object obj, Class clazz) {
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            dateFormat.setTimeZone(TimeZone.getTimeZone("CEST"));

            return new ObjectMapper().findAndRegisterModules().setDateFormat(dateFormat).convertValue(obj, clazz);
        } catch (Exception e) {
            log.info("FAILED - fromMapToObject; {}", e.getMessage());
            log.error("EXCEPTION - fromMapToObject", e);
            return null;
        }
    }

    public static Map<String, Object> fromObjectToMap(final Object obj) {
        try {
            return new ObjectMapper().findAndRegisterModules().convertValue(obj, Map.class);
        } catch (Exception e) {
            log.info("FAILED - fromObjectToMap; {}", e.getMessage());
            log.error("EXCEPTION - fromObjectToMap", e);
            return new HashMap<>();
        }
    }

    /**
     * Converts field of arguments to string or null in case of an error
     *
     * @param args arguments to be converted to string
     * @return arguments in string format
     */
    public static String arrayToString(final Object[] args) {
        try {
            if (args == null || args.length == 0)
                return "";
            else if (args.length == 1) {
                return (args[0] != null ? args[0].toString() : "null");
            } else {
                return Arrays.toString(args);
            }
        } catch (Exception e) {
            log.info("FAILED - arrayToString; {}", e.getMessage());
            log.error("EXCEPTION - arrayToString", e);
            return null;
        }
    }
}
