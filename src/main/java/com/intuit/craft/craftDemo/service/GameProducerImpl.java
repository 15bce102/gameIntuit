package com.intuit.craft.craftDemo.service;

import com.intuit.craft.craftDemo.entity.Game;
import com.intuit.craft.craftDemo.exception.PublishDataException;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.intuit.craft.craftDemo.constants.Constants.TOPIC_GAME;

@Service
public class GameProducerImpl implements GameProducer{

    private final Logger logger = LoggerFactory.getLogger(GameProducerImpl.class);
    @Autowired
    private GameConsumerImpl gameConsumerImpl;
    @Autowired
    private KafkaProducer<String, Game> kafkaProducer;

    public void publishData(Game game) throws PublishDataException {
        try {
            ProducerRecord<String, Game> gameRecord = new ProducerRecord<>(TOPIC_GAME, game);
            kafkaProducer.send(gameRecord);
            logger.info("Event published...");
            kafkaProducer.flush();

        } catch (Exception e) {
            throw new PublishDataException(e.getMessage());
        }
    }
}
