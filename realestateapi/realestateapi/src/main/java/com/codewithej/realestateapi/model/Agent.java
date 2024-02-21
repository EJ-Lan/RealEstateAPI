package com.codewithej.realestateapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Agent extends Person {

    @OneToMany(mappedBy ="agent")
    private Set<Property> managedProperties = new HashSet<>();

    @OneToMany(mappedBy = "agent")
    private Set<Client> clients = new HashSet<>();

    public Agent() {
    }

    public Set<Property> getManagedProperties() {
        return managedProperties;
    }

    public void setManagedProperties(Set<Property> managedProperties) {
        this.managedProperties = managedProperties;
    }

    public Set<Client> getClients() {
        return clients;
    }

    public void setClients(Set<Client> clients) {
        this.clients = clients;
    }
}
