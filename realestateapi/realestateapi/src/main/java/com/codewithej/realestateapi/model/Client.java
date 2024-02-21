package com.codewithej.realestateapi.model;

import jakarta.persistence.Entity;

@Entity
public class Client extends Person{

    private String preferences;

    public Client() {
    }


    public String getPreferences() {
        return preferences;
    }

    public void setPreferences(String preferences) {
        this.preferences = preferences;
    }
}
