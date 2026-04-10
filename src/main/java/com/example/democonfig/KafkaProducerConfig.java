package com.example.democonfig;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

/**
 * Kafka producer configuration class.
 * Defines how the Kafka producer is created and configured.
 */
@Configuration
public class KafkaProducerConfig {

    /**
     * Creates and configures the Kafka ProducerFactory.
     * ProducerFactory is responsible for creating Kafka producer instances.
     */
    @Bean
    public ProducerFactory<String, Object> producerFactory() {

        // Holds Kafka producer configuration properties
        Map<String, Object> configProps = new HashMap<>();

        // it tells to producer  that where kafka is running
        configProps.put(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                "localhost:9092"
        );

        // tells kafka how to convert key to byte kafka can not send obj it can send only bytes
        configProps.put(
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class
        );

        // tells kafka  how to convert value to bytes
        
        configProps.put(
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                JsonSerializer.class 
        );

        // Creates Kafka producer factory using the provided configuration
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    /**
     * Creates KafkaTemplate bean.
     * KafkaTemplate is used by producer services to send messages to Kafka.
     */
    @Bean
    public KafkaTemplate<String, Object> kafkaTemplate() {

        // KafkaTemplate internally uses ProducerFactory to send messages
        return new KafkaTemplate<>(producerFactory());
    }
}
