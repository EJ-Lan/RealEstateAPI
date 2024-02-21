package com.codewithej.realestateapi.service;

import com.codewithej.realestateapi.dto.PropertyDTO;
import com.codewithej.realestateapi.model.PropertyStatus;
import com.codewithej.realestateapi.model.PropertyType;

import java.util.Date;
import java.util.List;

public interface PropertyService {
    PropertyDTO createProperty(PropertyDTO propertyDTO);

    PropertyDTO updateProperty(Long id, PropertyDTO propertyDTO);

    void deleteProperty(Long id);

    PropertyDTO getPropertyById(Long id);

    List<PropertyDTO> getAllProperties();

    List<PropertyDTO> findByStatus(PropertyStatus status);

    List<PropertyDTO> findByType(PropertyType type);

    List<PropertyDTO> findByPriceRange(Double minPrice, Double maxPrice);

    List<PropertyDTO> findByBedrooms(Integer bedrooms);

    List<PropertyDTO> findByBathrooms(Integer bathrooms);

    List<PropertyDTO> findBySquareFootageRange(Integer minSquareFootage, Integer maxSquareFootage);

    List<PropertyDTO> findByListingDateAfter(Date date);
}
