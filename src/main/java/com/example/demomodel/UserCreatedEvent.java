package com.example.demomodel;

/**
 * This class represents the event data that will be sent to Kafka
 * when a new user is created.
 */
public class UserCreatedEvent {

    private String userId;  
    private String email;    

    
    
    public UserCreatedEvent() {
    }

   
    public UserCreatedEvent(String userId, String email) {
        this.userId = userId;
        this.email = email;
    }

    
    public String getUserId() {
        return userId;
    }

    
    public void setUserId(String userId) {
        this.userId = userId;
    }

    
    public String getEmail() {
        return email;
    }

    
    public void setEmail(String email) {
        this.email = email;
    }
}
