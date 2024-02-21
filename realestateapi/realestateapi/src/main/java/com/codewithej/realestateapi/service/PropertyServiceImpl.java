package com.codewithej.realestateapi.service;

import com.codewithej.realestateapi.dto.PropertyDTO;
import com.codewithej.realestateapi.model.PropertyStatus;
import com.codewithej.realestateapi.model.PropertyType;
import com.codewithej.realestateapi.repository.PropertyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public PropertyDTO createProperty(PropertyDTO propertyDTO) {
        return null;
    }

    @Override
    public PropertyDTO updateProperty(Long id, PropertyDTO propertyDTO) {
        return null;
    }

    @Override
    public void deleteProperty(Long id) {

    }

    @Override
    public PropertyDTO getPropertyById(Long id) {
        return null;
    }

    @Override
    public List<PropertyDTO> getAllProperties() {
        return null;
    }

    @Override
    public List<PropertyDTO> findByStatus(PropertyStatus status) {
        return null;
    }

    @Override
    public List<PropertyDTO> findByType(PropertyType type) {
        return null;
    }

    @Override
    public List<PropertyDTO> findByPriceRange(Double minPrice, Double maxPrice) {
        return null;
    }

    @Override
    public List<PropertyDTO> findByBedrooms(Integer bedrooms) {
        return null;
    }

    @Override
    public List<PropertyDTO> findByBathrooms(Integer bathrooms) {
        return null;
    }

    @Override
    public List<PropertyDTO> findBySquareFootageRange(Integer minSquareFootage, Integer maxSquareFootage) {
        return null;
    }

    @Override
    public List<PropertyDTO> findByListingDateAfter(Date date) {
        return null;
    }
}
