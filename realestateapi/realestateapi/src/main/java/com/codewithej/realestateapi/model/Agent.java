package com.codewithej.realestateapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents a real estate agent who manages properties and interacts with clients.
 * <p>
 * Inherits from the Person class to include basic personal details.
 * </p>
 */
@Entity
public class Agent extends Person {

    @OneToMany(mappedBy ="agent")
    private Set<Property> managedProperties = new HashSet<>();

    @OneToMany(mappedBy = "agent")
    private Set<Client> clients = new HashSet<>();

    /**
     * Default constructor for creating an instance of Agent.
     */
    public Agent() {
    }

    /**
     * Retrieves the set of properties managed by this agent.
     *
     * @return A set of {@link Property} instances managed by the agent.
     */
    public Set<Property> getManagedProperties() {
        return managedProperties;
    }

    /**
     * Sets the properties managed by this agent.
     *
     * @param managedProperties A set of {@link Property} instances to be managed by the agent.
     */
    public void setManagedProperties(Set<Property> managedProperties) {
        this.managedProperties = managedProperties;
    }

    /**
     * Retrieves the set of clients associated with this agent.
     *
     * @return A set of {@link Client} instances associated with the agent.
     */
    public Set<Client> getClients() {
        return clients;
    }

    /**
     * Sets the clients associated with this agent.
     *
     * @param clients A set of {@link Client} instances to be associated with the agent.
     */
    public void setClients(Set<Client> clients) {
        this.clients = clients;
    }
}
