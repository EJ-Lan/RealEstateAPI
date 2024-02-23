package com.codewithej.realestateapi.repository;

import com.codewithej.realestateapi.model.Property;
import com.codewithej.realestateapi.model.PropertyType;
import com.codewithej.realestateapi.model.PropertyStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {
    List<Property> findByStatus(PropertyStatus status);

    List<Property> findByType(PropertyType type);

    List<Property> findByPriceBetween(Double minPrice, Double maxPrice);

    List<Property> findByBedrooms(Integer bedrooms);

    List<Property> findByBathrooms(Integer bathrooms);

    List<Property> findBySquareFootageBetween(Integer minSquareFootage, Integer maxSquareFootage);

    List<Property> findByListingDateAfter(Date date);

}
