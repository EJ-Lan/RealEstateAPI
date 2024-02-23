package com.codewithej.realestateapi.dto;

import java.util.List;
import java.util.Set;

public class AgentDTO {
    private Long id;

    private String name;

    private String email;

    private String phoneNumber;

    private List<Long> managedPropertyIds;

    private Set<ClientDTO> clients;

    public AgentDTO() {
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

    public List<Long> getManagedPropertyIds() {
        return managedPropertyIds;
    }

    public void setManagedPropertyIds(List<Long> managedPropertyIds) {
        this.managedPropertyIds = managedPropertyIds;
    }

    public Set<ClientDTO> getClients() {
        return clients;
    }

    public void setClients(Set<ClientDTO> clients) {
        this.clients = clients;
    }
}
