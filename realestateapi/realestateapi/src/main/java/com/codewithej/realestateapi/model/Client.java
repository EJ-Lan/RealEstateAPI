package com.codewithej.realestateapi.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Client extends Person{
    private String preferences;
    @ManyToOne
    @JoinColumn(name="agent_id")
    private Agent agent;
    @ManyToMany
    @JoinTable(
            name = "client_property_interest",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "property_id")
    )
    private Set<Property> intrestedProperties = new HashSet<>();

    public Client() {
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
