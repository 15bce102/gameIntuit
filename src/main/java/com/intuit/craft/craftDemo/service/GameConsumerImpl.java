package com.intuit.craft.craftDemo.service;

import com.intuit.craft.craftDemo.entity.Game;
import com.intuit.craft.craftDemo.exception.ConsumeDataException;
import com.intuit.craft.craftDemo.repository.GameRepository;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import static com.intuit.craft.craftDemo.constants.Constants.TOPIC_GAME;

@Component
public class GameConsumerImpl implements GameConsumer{
    @Autowired
    private GameRepository gameRepository;

    Logger logger = LoggerFactory.getLogger(GameConsumerImpl.class);

    @KafkaListener(topics = TOPIC_GAME, groupId = "mygroup")
    public void startConsumer(ConsumerRecord<String, Game> record) throws ConsumeDataException {
        logger.info("CONSUMER STARTED");
        try {
            gameRepository.save(record.value());
            System.out.println("DATA CONSUMED");
        } catch (Exception e) {
            throw new ConsumeDataException(e.getMessage());
        }
    }
}
