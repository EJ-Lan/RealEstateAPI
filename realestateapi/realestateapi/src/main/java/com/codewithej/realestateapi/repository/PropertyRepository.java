package com.codewithej.realestateapi.repository;

import com.codewithej.realestateapi.model.Property;
import com.codewithej.realestateapi.model.PropertyType;
import com.codewithej.realestateapi.model.PropertyStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Repository interface for accessing and querying {@link Property} entities.
 * <p>
 * This interface extends JpaRepository, offering CRUD operations and enabling custom queries
 * to find properties based on various attributes such as status, type, price range,
 * number of bedrooms or bathrooms, square footage, and listing date.
 * </p>
 */
@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {

    /**
     * Finds properties by their status.
     *
     * @param status The {@link PropertyStatus} to filter properties by.
     * @return A list of properties matching the given status.
     */
    List<Property> findByStatus(PropertyStatus status);

    /**
     * Finds properties by their type.
     *
     * @param type The {@link PropertyType} to filter properties by.
     * @return A list of properties matching the given type.
     */
    List<Property> findByType(PropertyType type);

    /**
     * Finds properties within a specified price range.
     *
     * @param minPrice The minimum price of the property range.
     * @param maxPrice The maximum price of the property range.
     * @return A list of properties whose prices fall within the specified range.
     */
    List<Property> findByPriceBetween(Double minPrice, Double maxPrice);

    /**
     * Finds properties by the number of bedrooms.
     *
     * @param bedrooms The number of bedrooms to filter properties by.
     * @return A list of properties with the specified number of bedrooms.
     */
    List<Property> findByBedrooms(Integer bedrooms);

    /**
     * Finds properties by the number of bathrooms.
     *
     * @param bathrooms The number of bathrooms to filter properties by.
     * @return A list of properties with the specified number of bathrooms.
     */
    List<Property> findByBathrooms(Integer bathrooms);

    /**
     * Finds properties within a specified range of square footage.
     *
     * @param minSquareFootage The minimum square footage of the property range.
     * @param maxSquareFootage The maximum square footage of the property range.
     * @return A list of properties whose square footage falls within the specified range.
     */
    List<Property> findBySquareFootageBetween(Integer minSquareFootage, Integer maxSquareFootage);

    /**
     * Finds properties that were listed after a specified date.
     *
     * @param date The date to compare against property listing dates.
     * @return A list of properties listed after the specified date.
     */
    List<Property> findByListingDateAfter(Date date);
}
