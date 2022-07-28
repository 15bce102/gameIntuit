package com.intuit.craft.craftDemo.service;

import com.intuit.craft.craftDemo.entity.Game;
import com.intuit.craft.craftDemo.exception.ConsumeDataException;
import org.apache.kafka.clients.consumer.ConsumerRecord;

public interface GameConsumer {

    void startConsumer(ConsumerRecord<String, Game> record) throws ConsumeDataException;
}
