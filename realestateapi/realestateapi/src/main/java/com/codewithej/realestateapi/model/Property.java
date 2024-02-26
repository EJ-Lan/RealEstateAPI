package com.codewithej.realestateapi.model;

import jakarta.persistence.*;

import java.util.Date;

/**
 * Represents a real estate property, detailing its features such as type, status, and physical characteristics.
 * <p>
 * Associated with an agent responsible for managing the property.
 * </p>
 */
@Entity
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String address;

    private Double price;

    @Enumerated(EnumType.STRING)
    private PropertyType type;

    @Enumerated(EnumType.STRING)
    private PropertyStatus status;

    private Integer bedrooms;

    private Integer bathrooms;

    private Integer squareFootage;

    private Integer yearBuilt;

    @Temporal(TemporalType.DATE)
    private Date listingDate;

    @ManyToOne
    @JoinColumn(name = "agent_id")
    private Agent agent;

    /**
     * Default constructor for Property.
     */
    public Property() {
    }

    /**
     * Gets the unique identifier for this property.
     *
     * @return The ID of the property.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier for this property.
     *
     * @param id The ID to set for the property.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the address of this property.
     *
     * @return The address of the property.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the address of this property.
     *
     * @param address The address to set for the property.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets the price of this property.
     *
     * @return The price of the property.
     */
    public Double getPrice() {
        return price;
    }

    /**
     * Sets the price of this property.
     *
     * @param price The price to set for the property.
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * Gets the type of this property.
     *
     * @return The type of the property.
     */
    public PropertyType getType() {
        return type;
    }

    /**
     * Sets the type of this property.
     *
     * @param type The type to set for the property.
     */
    public void setType(PropertyType type) {
        this.type = type;
    }

    /**
     * Gets the status of this property.
     *
     * @return The status of the property.
     */
    public PropertyStatus getStatus() {
        return status;
    }

    /**
     * Sets the status of this property.
     *
     * @param status The status to set for the property.
     */
    public void setStatus(PropertyStatus status) {
        this.status = status;
    }

    /**
     * Gets the number of bedrooms in this property.
     *
     * @return The number of bedrooms in the property.
     */
    public Integer getBedrooms() {
        return bedrooms;
    }

    /**
     * Sets the number of bedrooms in this property.
     *
     * @param bedrooms The number of bedrooms to set for the property.
     */
    public void setBedrooms(Integer bedrooms) {
        this.bedrooms = bedrooms;
    }

    /**
     * Gets the number of bathrooms in this property.
     *
     * @return The number of bathrooms in the property.
     */
    public Integer getBathrooms() {
        return bathrooms;
    }

    /**
     * Sets the number of bathrooms in this property.
     *
     * @param bathrooms The number of bathrooms to set for the property.
     */
    public void setBathrooms(Integer bathrooms) {
        this.bathrooms = bathrooms;
    }

    /**
     * Gets the square footage of this property.
     *
     * @return The square footage of the property.
     */
    public Integer getSquareFootage() {
        return squareFootage;
    }

    /**
     * Sets the square footage of this property.
     *
     * @param squareFootage The square footage to set for the property.
     */
    public void setSquareFootage(Integer squareFootage) {
        this.squareFootage = squareFootage;
    }

    /**
     * Gets the year this property was built.
     *
     * @return The year the property was built.
     */
    public Integer getYearBuilt() {
        return yearBuilt;
    }

    /**
     * Sets the year this property was built.
     *
     * @param yearBuilt The year to set for the property's construction.
     */
    public void setYearBuilt(Integer yearBuilt) {
        this.yearBuilt = yearBuilt;
    }

    /**
     * Gets the listing date of this property.
     *
     * @return The listing date of the property.
     */
    public Date getListingDate() {
        return listingDate;
    }

    /**
     * Sets the listing date of this property.
     *
     * @param listingDate The listing date to set for the property.
     */
    public void setListingDate(Date listingDate) {
        this.listingDate = listingDate;
    }

    /**
     * Gets the agent associated with this property.
     *
     * @return The agent associated with the property.
     */
    public Agent getAgent() {
        return agent;
    }

    /**
     * Sets the agent associated with this property.
     *
     * @param agent The agent to associate with the property.
     */
    public void setAgent(Agent agent) {
        this.agent = agent;
    }
}
