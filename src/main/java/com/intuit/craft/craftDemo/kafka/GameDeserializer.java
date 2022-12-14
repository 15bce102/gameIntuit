package com.intuit.craft.craftDemo.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;
import org.springframework.util.SerializationUtils;

import java.io.Serializable;
import java.util.Map;

public class GameDeserializer<T extends Serializable> implements Deserializer<T> {
    public static final String VALUE_CLASS_NAME_CONFIG = "value.class.name";
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @SuppressWarnings("unchecked")
    @Override
    public T deserialize(String topic, byte[] objectData) {
        return (objectData == null) ? null : (T) SerializationUtils.deserialize(objectData);
    }

    @Override
    public void close() {
    }
}
