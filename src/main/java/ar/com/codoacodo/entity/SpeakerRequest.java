package ar.com.codoacodo.entity;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Optional;

public class SpeakerRequest {
    private String name;
    private String lastName;
    private String email;
    private String topic;

    public SpeakerRequest() {
    }

    public SpeakerRequest(String name, String lastName, String email, String topic) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.topic = topic;
    }

    public void setName(String name) { this.name = name; }
    public String getName() {
        return this.name;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getTopic() {
        return topic;
    }
    public void setTopic(String topic) {
        this.topic = topic;
    }
}
