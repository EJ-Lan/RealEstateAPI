package com.codewithej.realestateapi.dto;

import com.codewithej.realestateapi.model.Agent;
import com.codewithej.realestateapi.model.Property;

import java.util.Set;

public class ClientDTO {
    private Long id;

    private String name;

    private String email;

    private String phoneNumber;

    private String preferences;

    private Agent agent;

    private Set<Property> intrestedProperties;

    public ClientDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPreferences() {
        return preferences;
    }

    public void setPreferences(String preferences) {
        this.preferences = preferences;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public Set<Property> getIntrestedProperties() {
        return intrestedProperties;
    }

    public void setIntrestedProperties(Set<Property> intrestedProperties) {
        this.intrestedProperties = intrestedProperties;
    }
}
