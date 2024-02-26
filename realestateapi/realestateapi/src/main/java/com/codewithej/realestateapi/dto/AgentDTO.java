package com.codewithej.realestateapi.dto;

import java.util.List;
import java.util.Set;

/**
 * Data Transfer Object for agents.
 * <p>
 * This class is used to transfer agent data between the server and clients, encapsulating
 * agent details and the relationships with properties and clients.
 * </p>
 */
public class AgentDTO {
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private List<Long> managedPropertyIds; // IDs of properties managed by the agent
    private Set<ClientDTO> clients; // Clients associated with the agent

    /**
     * Default constructor.
     */
    public AgentDTO() {
    }

    /**
     * Gets the ID of the agent.
     *
     * @return The ID of the agent.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the agent.
     *
     * @param id The ID to set for the agent.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the name of the agent.
     *
     * @return The name of the agent.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the agent.
     *
     * @param name The name to set for the agent.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the email address of the agent.
     *
     * @return The email address of the agent.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the agent.
     *
     * @param email The email address to set for the agent.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the phone number of the agent.
     *
     * @return The phone number of the agent.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the phone number of the agent.
     *
     * @param phoneNumber The phone number to set for the agent.
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Gets the list of IDs for properties managed by the agent.
     *
     * @return A list of property IDs managed by the agent.
     */
    public List<Long> getManagedPropertyIds() {
        return managedPropertyIds;
    }

    /**
     * Sets the list of IDs for properties managed by the agent.
     *
     * @param managedPropertyIds The list of property IDs to set as managed by the agent.
     */
    public void setManagedPropertyIds(List<Long> managedPropertyIds) {
        this.managedPropertyIds = managedPropertyIds;
    }

    /**
     * Gets the set of clients associated with the agent.
     *
     * @return A set of {@link ClientDTO}s associated with the agent.
     */
    public Set<ClientDTO> getClients() {
        return clients;
    }

    /**
     * Sets the set of clients associated with the agent.
     *
     * @param clients The set of {@link ClientDTO}s to associate with the agent.
     */
    public void setClients(Set<ClientDTO> clients) {
        this.clients = clients;
    }
}
