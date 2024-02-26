package com.codewithej.realestateapi.service;

import com.codewithej.realestateapi.dto.PropertyDTO;
import com.codewithej.realestateapi.model.PropertyStatus;
import com.codewithej.realestateapi.model.PropertyType;

import java.util.Date;
import java.util.List;

/**
 * Interface for property-related operations.
 * <p>
 * Defines the contract for services handling the creation, modification, deletion,
 * and querying of property entities. This includes operations to retrieve properties
 * based on various criteria such as status, type, price range, number of bedrooms or bathrooms,
 * square footage, and listing date.
 * </p>
 */
public interface PropertyService {

    /**
     * Creates a new property.
     *
     * @param propertyDTO The data transfer object containing the property's details.
     * @return The created property, as a DTO.
     */
    PropertyDTO createProperty(PropertyDTO propertyDTO);

    /**
     * Updates an existing property.
     *
     * @param id The ID of the property to update.
     * @param propertyDTO The updated property details.
     * @return The updated property, as a DTO.
     */
    PropertyDTO updateProperty(Long id, PropertyDTO propertyDTO);

    /**
     * Deletes a property by its ID.
     *
     * @param id The ID of the property to delete.
     */
    void deleteProperty(Long id);

    /**
     * Retrieves a property by its ID.
     *
     * @param id The ID of the property.
     * @return The requested property, as a DTO.
     */
    PropertyDTO getPropertyById(Long id);

    /**
     * Retrieves all properties.
     *
     * @return A list of all properties, as DTOs.
     */
    List<PropertyDTO> getAllProperties();

    /**
     * Finds properties by their status.
     *
     * @param status The status to filter properties by.
     * @return A list of properties matching the given status.
     */
    List<PropertyDTO> findByStatus(PropertyStatus status);

    /**
     * Finds properties by their type.
     *
     * @param type The type to filter properties by.
     * @return A list of properties matching the given type.
     */
    List<PropertyDTO> findByType(PropertyType type);

    /**
     * Finds properties within a specified price range.
     *
     * @param minPrice The minimum price of the range.
     * @param maxPrice The maximum price of the range.
     * @return A list of properties whose prices fall within the specified range.
     */
    List<PropertyDTO> findByPriceRange(Double minPrice, Double maxPrice);

    /**
     * Finds properties by the number of bedrooms.
     *
     * @param bedrooms The number of bedrooms to filter properties by.
     * @return A list of properties with the specified number of bedrooms.
     */
    List<PropertyDTO> findByBedrooms(Integer bedrooms);

    /**
     * Finds properties by the number of bathrooms.
     *
     * @param bathrooms The number of bathrooms to filter properties by.
     * @return A list of properties with the specified number of bathrooms.
     */
    List<PropertyDTO> findByBathrooms(Integer bathrooms);

    /**
     * Finds properties within a specified square footage range.
     *
     * @param minSquareFootage The minimum square footage of the range.
     * @param maxSquareFootage The maximum square footage of the range.
     * @return A list of properties whose square footage falls within the specified range.
     */
    List<PropertyDTO> findBySquareFootageRange(Integer minSquareFootage, Integer maxSquareFootage);

    /**
     * Finds properties that were listed after a specified date.
     *
     * @param date The date to compare against property listing dates.
     * @return A list of properties listed after the specified date.
     */
    List<PropertyDTO> findByListingDateAfter(Date date);
}
