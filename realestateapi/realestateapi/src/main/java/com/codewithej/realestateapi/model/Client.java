package com.codewithej.realestateapi.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents a client interested in buying or renting properties.
 * <p>
 * Stores client preferences as a JSON string and maintains a relationship with an agent.
 * </p>
 */
@Entity
public class Client extends Person{
    private String preferences; // A Json of preferences

    @ManyToOne
    @JoinColumn(name="agent_id")
    private Agent agent;

    @ManyToMany
    @JoinTable(
            name = "client_property_interest",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "property_id")
    )
    private Set<Property> interestedProperties = new HashSet<>();

    /**
     * Default constructor for creating an instance of Client.
     */
    public Client() {
    }

    /**
     * Retrieves the client's preferences in JSON format.
     *
     * @return A string containing the JSON representation of the client's preferences.
     */
    public String getPreferences() {
        return preferences;
    }

    /**
     * Sets the client's preferences.
     *
     * @param preferences A JSON string containing the client's preferences.
     */
    public void setPreferences(String preferences) {
        this.preferences = preferences;
    }

    /**
     * Retrieves the agent associated with this client.
     *
     * @return The {@link Agent} instance associated with this client.
     */
    public Agent getAgent() {
        return agent;
    }

    /**
     * Sets the agent associated with this client.
     *
     * @param agent The {@link Agent} to be associated with this client.
     */
    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    /**
     * Retrieves the properties in which the client is interested.
     *
     * @return A set of {@link Property} instances the client is interested in.
     */
    public Set<Property> getInterestedProperties() {
        return interestedProperties;
    }

    /**
     * Sets the properties in which the client is interested.
     *
     * @param interestedProperties A set of {@link Property} instances the client is interested in.
     */
    public void setInterestedProperties(Set<Property> interestedProperties) {
        this.interestedProperties = interestedProperties;
    }
}
