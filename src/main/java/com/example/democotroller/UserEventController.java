package com.example.democotroller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demomodel.UserCreatedEvent;
import com.example.demoproducer.UserEventProducer;


/**
 * REST controller that exposes APIs to publish user-related events to Kafka.
 */
@RestController
@RequestMapping("/users")
public class UserEventController {

    // Producer service responsible for sending events to Kafka
    private final UserEventProducer userEventProducer;

     
    public UserEventController(UserEventProducer userEventProducer) {
        this.userEventProducer = userEventProducer;
    }

    /**
     * API endpoint to create a user event and publish it to Kafka.
     */
    @PostMapping
    public ResponseEntity<String> createUser(
            @RequestBody UserCreatedEvent event) {

        // Calls producer service to publish the event to Kafka
        userEventProducer.sendUserCreatedEvent(event);

        // Returns success response once the event is published
        return ResponseEntity.ok("User event published to Kafka");
    }
}



