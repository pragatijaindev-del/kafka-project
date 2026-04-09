package com.example.demoproducer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.demomodel.UserCreatedEvent;


/**
 * This service is responsible for publishing user-related events to Kafka.
 */
@Service
public class UserEventProducer {

    // Logger used for tracking event publishing and debugging
    private static final Logger log =
            LoggerFactory.getLogger(UserEventProducer.class);

    // Kafka topic name where user creation events are published
    private static final String TOPIC_NAME = "user.created";

    // KafkaTemplate is the main Kafka client used to send messages
    private final KafkaTemplate<String, Object> kafkaTemplate;

    
    public UserEventProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    /**
     * Publishes a UserCreatedEvent to Kafka.
     * This method is called after a user is successfully created.
     */
    public void sendUserCreatedEvent(UserCreatedEvent event) {

        // Log the event publishing for observability and troubleshooting
        
    	log.info(
                "Publishing UserCreatedEvent for userId={}",
                event.getUserId()
        );

        // Send the event to Kafka here key is userID and value is event obj
        
        kafkaTemplate.send(
                TOPIC_NAME,
                event.getUserId(),
                event
        );
    }
}