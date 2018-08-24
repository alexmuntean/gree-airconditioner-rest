package com.gree.airconditioner.dto.packs;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gree.airconditioner.binding.GreeDeviceBinding;
import com.gree.airconditioner.dto.status.*;
import com.gree.airconditioner.util.CryptoUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class StatusResponsePack {
    private static final Logger log = LogManager.getLogger(StatusResponsePack.class);

    private final GreeDeviceBinding binding;
    private final String json;

    private StatusResponsePack(String json, GreeDeviceBinding binding) {
        this.json = json;
        this.binding = binding;
    }

    public GreeDeviceStatus toObject() {
        JsonNode packJsonNode = getPackJsonNode();
        Map<String, Integer> properties = getPropertiesAndValuesAsMap(packJsonNode);

        return getGreeDeviceStatus(properties);
    }

    private GreeDeviceStatus getGreeDeviceStatus(Map<String, Integer> properties) {
        GreeDeviceStatus status = new GreeDeviceStatus();
        for (Field field : GreeDeviceStatus.class.getDeclaredFields()) {
            String property = getPropertyName(field);
            if (property == null) {
                continue;
            }

            Integer value = properties.get(property);
            if (value == null) {
                if (log.isDebugEnabled()) {
                    log.debug("No value set for {}", property);
                }
                continue;
            }

            if (field.getType().equals(Switch.class)) {
                setValue(status, field, Switch.fromCode(value));
            } else if (field.getType().equals(SwingDirection.class)) {
                setValue(status, field, new SwingDirection(value));
            } else if (field.getType().equals(FanMode.class)) {
                setValue(status, field, FanMode.fromCode(value));
            } else if (field.getType().equals(OperationMode.class)) {
                setValue(status, field, OperationMode.fromCode(value));
            }
        }
        try {
            Field temperatureField = Temperature.class.getDeclaredField("temperature");
            String temperatureProperty = getPropertyName(temperatureField);
            Integer temperatureValue = properties.get(temperatureProperty);

            Field unitField = null;
            unitField = Temperature.class.getDeclaredField("unit");
            String unitProperty = getPropertyName(unitField);

            Integer unitValue = properties.get(unitProperty);
            Temperature temperature = new Temperature(temperatureValue, TemperatureUnit.fromCode(unitValue));
            status.setTemperature(temperature);

        } catch (NoSuchFieldException e) {
            log.error(e);
        }

        return status;
    }

    private Object setValue(GreeDeviceStatus status, Field field, Object value) {
        String name = field.getName();
        String methodName = "set" + name.substring(0, 1).toUpperCase() + name.substring(1);
        try {
            Method method = GreeDeviceStatus.class.getMethod(methodName, value.getClass());
            Object result = method.invoke(status, value);
            return result;
        } catch (NoSuchMethodException e) {
            log.error("Can't find method {}", methodName, e);
        } catch (IllegalAccessException e) {
            log.error("Method not accessible {}", methodName, e);
        } catch (InvocationTargetException e) {
            log.error("Can't invoke method {}", methodName, e);
        }
        return null;
    }

    private String getPropertyName(Field field) {
        if (!field.isAnnotationPresent(JsonProperty.class)) {
            return null;
        }
        JsonProperty property = field.getDeclaredAnnotation(JsonProperty.class);
        return property.value();
    }

    private JsonNode getPackJsonNode() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = null;
            jsonNode = objectMapper.readTree(json);
            JsonNode packNode = jsonNode.get("pack");
            String encryptedPack = packNode.asText();
            String packJson = CryptoUtil.decryptPack(binding.getAesKey().getBytes(), encryptedPack);

            return objectMapper.readTree(packJson);
        } catch (IOException e) {
            log.error("Can't get pack from json {}", json, e);
        }
        return null;
    }

    private Map<String, Integer> getPropertiesAndValuesAsMap(JsonNode packJsonNode) {
        Map<String, Integer> properties = new HashMap<>();
        JsonNode colsNode = packJsonNode.get("cols");
        List<String> colsList = new LinkedList<>();
        for (final JsonNode colNode : colsNode) {
            colsList.add(colNode.asText());
        }

        JsonNode datsNode = packJsonNode.get("dat");
        List<Integer> datList = new LinkedList<>();
        for (final JsonNode datNode : datsNode) {
            datList.add(datNode.asInt());
        }

        for (int i = 0; i < colsList.size(); i++) {
            String col = colsList.get(i);
            properties.put(col, datList.get(i));
        }

        return properties;
    }

    public static StatusResponsePack build(String json, GreeDeviceBinding binding) {
        return new StatusResponsePack(json, binding);
    }
}
