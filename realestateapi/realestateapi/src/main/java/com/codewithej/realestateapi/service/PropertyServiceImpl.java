package com.codewithej.realestateapi.service;

import com.codewithej.realestateapi.dto.PropertyDTO;
import com.codewithej.realestateapi.model.Property;
import com.codewithej.realestateapi.model.PropertyStatus;
import com.codewithej.realestateapi.model.PropertyType;
import com.codewithej.realestateapi.repository.PropertyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PropertyServiceImpl implements PropertyService {

    private final PropertyRepository propertyRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public PropertyServiceImpl(PropertyRepository propertyRepository, ModelMapper modelMapper) {
        this.propertyRepository = propertyRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public PropertyDTO createProperty(PropertyDTO propertyDTO) {
        Property property = modelMapper.map(propertyDTO, Property.class);
        property = propertyRepository.save(property);
        return modelMapper.map(property, PropertyDTO.class);
    }

    @Override
    @Transactional
    public PropertyDTO updateProperty(Long id, PropertyDTO propertyDTO) {
        Property property = propertyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Property not found with id: " + id));
        modelMapper.map(propertyDTO, property); // Update the entity with DTO data
        property = propertyRepository.save(property);
        return modelMapper.map(property, PropertyDTO.class);
    }

    @Override
    @Transactional
    public void deleteProperty(Long id) {
        propertyRepository.deleteById(id);
    }

    @Override
    public PropertyDTO getPropertyById(Long id) {
        Property property = propertyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Property not found with id: " + id));
        return modelMapper.map(property, PropertyDTO.class);
    }

    @Override
    public List<PropertyDTO> getAllProperties() {
        return propertyRepository.findAll().stream()
                .map(property -> modelMapper.map(property, PropertyDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PropertyDTO> findByStatus(PropertyStatus status) {
        return propertyRepository.findByStatus(status).stream()
                .map(property -> modelMapper.map(property, PropertyDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PropertyDTO> findByType(PropertyType type) {
        return propertyRepository.findByType(type).stream()
                .map(property -> modelMapper.map(property, PropertyDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PropertyDTO> findByPriceRange(Double minPrice, Double maxPrice) {
        return propertyRepository.findByPriceBetween(minPrice, maxPrice).stream()
                .map(property -> modelMapper.map(property, PropertyDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PropertyDTO> findByBedrooms(Integer bedrooms) {
        return propertyRepository.findByBedrooms(bedrooms).stream()
                .map(property -> modelMapper.map(property, PropertyDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PropertyDTO> findByBathrooms(Integer bathrooms) {
        return propertyRepository.findByBathrooms(bathrooms).stream()
                .map(property -> modelMapper.map(property,PropertyDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PropertyDTO> findBySquareFootageRange(Integer minSquareFootage, Integer maxSquareFootage) {
        return propertyRepository.findBySquareFootageBetween(minSquareFootage, maxSquareFootage).stream()
                .map(property -> modelMapper.map(property, PropertyDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PropertyDTO> findByListingDateAfter(Date date) {
        return propertyRepository.findByListingDateAfter(date).stream()
                .map(property -> modelMapper.map(property, PropertyDTO.class))
                .collect(Collectors.toList());
    }
}
