package com.codewithej.realestateapi.dto;

import com.codewithej.realestateapi.model.PropertyStatus;
import com.codewithej.realestateapi.model.PropertyType;

import java.util.Date;

/**
 * Data Transfer Object for property entities.
 * <p>
 * Encapsulates the data of a property, including its basic details and attributes like type and status.
 * This class is used to transfer property data between the server and clients in a decoupled and simplified manner.
 * </p>
 */
public class PropertyDTO {
    private Long id;
    private String address;
    private Double price;
    private PropertyType type;
    private PropertyStatus status;
    private Integer bedrooms;
    private Integer bathrooms;
    private Integer squareFootage;
    private Integer yearBuilt;
    private Date listingDate;

    /**
     * Default constructor.
     */
    public PropertyDTO() {
    }

    // Getter and setter methods follow

    /**
     * Gets the ID of the property.
     *
     * @return The ID of the property.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the property.
     *
     * @param id The new ID for the property.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the address of the property.
     *
     * @return The address of the property.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the address of the property.
     *
     * @param address The new address for the property.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets the price of the property.
     *
     * @return The price of the property.
     */
    public Double getPrice() {
        return price;
    }

    /**
     * Sets the price of the property.
     *
     * @param price The new price for the property.
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * Gets the type of the property.
     *
     * @return The type of the property.
     */
    public PropertyType getType() {
        return type;
    }

    /**
     * Sets the type of the property.
     *
     * @param type The new type for the property.
     */
    public void setType(PropertyType type) {
        this.type = type;
    }

    /**
     * Gets the status of the property.
     *
     * @return The status of the property.
     */
    public PropertyStatus getStatus() {
        return status;
    }

    /**
     * Sets the status of the property.
     *
     * @param status The new status for the property.
     */
    public void setStatus(PropertyStatus status) {
        this.status = status;
    }

    /**
     * Gets the number of bedrooms in the property.
     *
     * @return The number of bedrooms in the property.
     */
    public Integer getBedrooms() {
        return bedrooms;
    }

    /**
     * Sets the number of bedrooms in the property.
     *
     * @param bedrooms The new number of bedrooms for the property.
     */
    public void setBedrooms(Integer bedrooms) {
        this.bedrooms = bedrooms;
    }

    /**
     * Gets the number of bathrooms in the property.
     *
     * @return The number of bathrooms in the property.
     */
    public Integer getBathrooms() {
        return bathrooms;
    }

    /**
     * Sets the number of bathrooms in the property.
     *
     * @param bathrooms The new number of bathrooms for the property.
     */
    public void setBathrooms(Integer bathrooms) {
        this.bathrooms = bathrooms;
    }

    /**
     * Gets the square footage of the property.
     *
     * @return The square footage of the property.
     */
    public Integer getSquareFootage() {
        return squareFootage;
    }

    /**
     * Sets the square footage of the property.
     *
     * @param squareFootage The new square footage for the property.
     */
    public void setSquareFootage(Integer squareFootage) {
        this.squareFootage = squareFootage;
    }

    /**
     * Gets the year the property was built.
     *
     * @return The year the property was built.
     */
    public Integer getYearBuilt() {
        return yearBuilt;
    }

    /**
     * Sets the year the property was built.
     *
     * @param yearBuilt The new construction year for the property.
     */
    public void setYearBuilt(Integer yearBuilt) {
        this.yearBuilt = yearBuilt;
    }

    /**
     * Gets the listing date of the property.
     *
     * @return The listing date of the property.
     */
    public Date getListingDate() {
        return listingDate;
    }

    /**
     * Sets the listing date of the property.
     *
     * @param listingDate The new listing date for the property.
     */
    public void setListingDate(Date listingDate) {
        this.listingDate = listingDate;
    }
}
