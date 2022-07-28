package com.intuit.craft.craftDemo.kafka;


import org.apache.kafka.common.serialization.Serializer;
import org.springframework.util.SerializationUtils;

import java.io.Serializable;
import java.util.Map;

public class GameSerializer<T extends Serializable> implements Serializer<T> {
    public GameSerializer(){}
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public byte[] serialize(String topic, T data) {
        return SerializationUtils.serialize(data);
    }

    @Override
    public void close() {
    }
}
