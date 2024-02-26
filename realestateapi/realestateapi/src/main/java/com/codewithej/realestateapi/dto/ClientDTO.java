package com.codewithej.realestateapi.dto;

import com.codewithej.realestateapi.model.Agent;
import com.codewithej.realestateapi.model.Property;

import java.util.Set;

/**
 * Data Transfer Object for clients.
 * <p>
 * This class is used to transfer client data between the server and clients. It encapsulates
 * client details including personal information, preferences, their associated agent, and properties
 * they are interested in. Note: The Agent and Property objects should be used carefully within DTOs,
 * ideally in a simplified form or as IDs to avoid circular dependencies or excessive data transfer.
 * </p>
 */
public class ClientDTO {
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private String preferences; // Client-specific preferences, possibly in JSON format.
    private Agent agent; // This should be a simplified version or an ID reference in a real DTO scenario.
    private Set<Property> interestedProperties; // Similarly, consider using IDs or simplified versions.

    /**
     * Default constructor.
     */
    public ClientDTO() {
    }

    // Getter and setter methods follow

    /**
     * Gets the ID of the client.
     *
     * @return The ID of the client.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the client.
     *
     * @param id The new ID for the client.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the name of the client.
     *
     * @return The name of the client.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the client.
     *
     * @param name The new name for the client.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the email address of the client.
     *
     * @return The email address of the client.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the client.
     *
     * @param email The new email address for the client.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the phone number of the client.
     *
     * @return The phone number of the client.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the phone number of the client.
     *
     * @param phoneNumber The new phone number for the client.
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Gets the preferences of the client.
     *
     * @return The preferences of the client.
     */
    public String getPreferences() {
        return preferences;
    }

    /**
     * Sets the preferences of the client.
     *
     * @param preferences The new preferences for the client.
     */
    public void setPreferences(String preferences) {
        this.preferences = preferences;
    }

    /**
     * Gets the agent associated with the client.
     *
     * @return The agent associated with the client.
     */
    public Agent getAgent() {
        return agent;
    }

    /**
     * Sets the agent associated with the client.
     *
     * @param agent The new agent for the client.
     */
    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    /**
     * Gets the properties the client is interested in.
     *
     * @return The properties the client is interested in.
     */
    public Set<Property> getInterestedProperties() {
        return interestedProperties;
    }

    /**
     * Sets the properties the client is interested in.
     *
     * @param interestedProperties The new set of properties for the client.
     */
    public void setInterestedProperties(Set<Property> interestedProperties) {
        this.interestedProperties = interestedProperties;
    }
}
