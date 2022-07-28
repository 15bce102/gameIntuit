package com.intuit.craft.craftDemo.configuration;

import com.intuit.craft.craftDemo.entity.Game;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

import static com.intuit.craft.craftDemo.constants.Constants.APPLICATION_ID_CONFIG;
import static com.intuit.craft.craftDemo.constants.Constants.BOOTSTRAP_SERVER;

@Configuration
public class KafkaConfiguration {
    @Bean
    public KafkaProducer<String, Game> kafkaProducer() {
        return new KafkaProducer<>(getKafkaProducerConfig());
    }

    private Properties getKafkaProducerConfig() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVER);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "com.intuit.craft.craftDemo.kafka.GameSerializer");
        props.put(ProducerConfig.CLIENT_ID_CONFIG, APPLICATION_ID_CONFIG);
        return props;
    }
}
