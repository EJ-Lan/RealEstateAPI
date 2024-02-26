package com.codewithej.realestateapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Abstract base class for individuals involved in the real estate process,
 * including agents and clients.
 * <p>>
 * Contains common attributes like name, email, and phone number.
 * </p>
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @Email(message = "Invalid email format")
    private String email;

    @Size(min = 10, max = 15, message = "Phone number must be between 10 and 15 characters")
    private String phoneNumber;

    /**
     * Default constructor for creating an instance of Person.
     */
    public Person() {
    }

    /**
     * Retrieves the ID of the person.
     *
     * @return The unique ID of the person.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the person.
     *
     * @param id The unique ID for the person.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retrieves the name of the person.
     *
     * @return The name of the person.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the person.
     *
     * @param name The name to be set for the person.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the email of the person.
     *
     * @return The email address of the person.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the person.
     *
     * @param email The email address to be set for the person.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retrieves the phone number of the person.
     *
     * @return The phone number of the person.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the phone number of the person.
     *
     * @param phoneNumber The phone number to be set for the person.
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
