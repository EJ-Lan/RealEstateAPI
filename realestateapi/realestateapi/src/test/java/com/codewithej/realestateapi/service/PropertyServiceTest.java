package com.codewithej.realestateapi.service;

import com.codewithej.realestateapi.dto.PropertyDTO;
import com.codewithej.realestateapi.model.Property;
import com.codewithej.realestateapi.model.PropertyStatus;
import com.codewithej.realestateapi.model.PropertyType;
import com.codewithej.realestateapi.repository.PropertyRepository;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@Import({PropertyServiceImpl.class, ModelMapper.class})
public class PropertyServiceTest {

    @Autowired
    private PropertyService propertyService;

    @MockBean
    private PropertyRepository propertyRepository;

    private Property property;
    private PropertyDTO propertyDTO;

    @BeforeEach
    void setUp() {
        property = new Property();
        property.setId(1L);
        property.setAddress("123 Main St");
        property.setPrice(250000.00);
        property.setType(PropertyType.RESIDENTIAL);
        property.setStatus(PropertyStatus.AVAILABLE);
        property.setBedrooms(3);
        property.setBathrooms(2);
        property.setSquareFootage(1500);
        property.setYearBuilt(1990);
        property.setListingDate(new Date());

        propertyDTO = new PropertyDTO();
        propertyDTO.setId(property.getId());
        propertyDTO.setAddress(property.getAddress());
        propertyDTO.setPrice(property.getPrice());
        propertyDTO.setType(property.getType());
        propertyDTO.setStatus(property.getStatus());
        propertyDTO.setBedrooms(property.getBedrooms());
        propertyDTO.setBathrooms(property.getBathrooms());
        propertyDTO.setSquareFootage(property.getSquareFootage());
        propertyDTO.setYearBuilt(property.getYearBuilt());
        propertyDTO.setListingDate(property.getListingDate());
    }

    @Test
    void createPropertyShouldSavePropertyCorrectly() {
        when(propertyRepository.save(any(Property.class))).thenReturn(property);

        PropertyDTO result = propertyService.createProperty(propertyDTO);

        assertThat(result.getId()).isEqualTo(property.getId());
        verify(propertyRepository, times(1)).save(any(Property.class));
    }

    @Test void updatePropertyShouldUpdatePropertyCorrectly() {
        when(propertyRepository.findById(anyLong())).thenReturn(java.util.Optional.of(property));
        when(propertyRepository.save(any(Property.class))).thenReturn(property);

        PropertyDTO updatedDTO = new PropertyDTO();
        updatedDTO.setAddress("Updated Address");
        PropertyDTO result = propertyService.updateProperty(1L, updatedDTO);

        assertThat(result.getAddress()).isEqualTo("Updated Address");
        verify(propertyRepository, times(1)).save(any(Property.class));
    }

    @Test
    void deletePropertyShouldDeletePropertyCorrectly() {
        doNothing().when(propertyRepository).deleteById(anyLong());

        propertyService.deleteProperty(1L);

        verify(propertyRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void getAllPropertiesShouldReturnListOfProperties() {
        when(propertyRepository.findAll()).thenReturn(Collections.singletonList(property));

        List<PropertyDTO> result = propertyService.getAllProperties();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getId()).isEqualTo(property.getId());
    }

    @Test
    void findByStatusShouldReturnFilteredProperties() {
        when(propertyRepository.findByStatus(PropertyStatus.AVAILABLE))
                .thenReturn(Collections.singletonList(property));

        List<PropertyDTO> results = propertyService.findByStatus(PropertyStatus.AVAILABLE);

        assertThat(results).hasSize(1);
        assertThat(results.get(0).getStatus()).isEqualTo(PropertyStatus.AVAILABLE);
        verify(propertyRepository, times(1)).findByStatus(PropertyStatus.AVAILABLE);
    }

    @Test
    void findByTypeShouldReturnFilteredProperties() {
        when(propertyRepository.findByType(PropertyType.RESIDENTIAL))
                .thenReturn(Collections.singletonList(property));

        List<PropertyDTO> results = propertyService.findByType(PropertyType.RESIDENTIAL);

        assertThat(results).hasSize(1);
        assertThat(results.get(0).getType()).isEqualTo(PropertyType.RESIDENTIAL);
        verify(propertyRepository, times(1)).findByType(PropertyType.RESIDENTIAL);
    }

    @Test
    void findByPriceRangeShouldReturnFilteredProperties() {
        Double minPrice = 200000.00;
        Double maxPrice = 300000.00;
        when(propertyRepository.findByPriceBetween(minPrice, maxPrice))
                .thenReturn(Collections.singletonList(property));

        List<PropertyDTO> results = propertyService.findByPriceRange(minPrice, maxPrice);

        assertThat(results).hasSize(1);
        assertThat(results.get(0).getPrice()).isBetween(minPrice, maxPrice);
        verify(propertyRepository, times(1)).findByPriceBetween(minPrice, maxPrice);
    }

    @Test
    void findByBedroomsShouldReturnPropertiesWithGivenBedrooms() {
        int bedrooms = 3;
        when(propertyRepository.findByBedrooms(bedrooms))
                .thenReturn(Collections.singletonList(property));

        List<PropertyDTO> results = propertyService.findByBedrooms(bedrooms);

        assertThat(results).hasSize(1);
        assertThat(results.get(0).getBedrooms()).isEqualTo(bedrooms);
        verify(propertyRepository, times(1)).findByBedrooms(bedrooms);
    }

    @Test
    void findByBathroomsShouldReturnPropertiesWithGivenBathrooms() {
        int bathrooms = 2;
        when(propertyRepository.findByBathrooms(bathrooms))
                .thenReturn(Collections.singletonList(property));

        List<PropertyDTO> results = propertyService.findByBathrooms(bathrooms);

        assertThat(results).hasSize(1);
        assertThat(results.get(0).getBathrooms()).isEqualTo(bathrooms);
        verify(propertyRepository, times(1)).findByBathrooms(bathrooms);
    }

    @Test
    void findBySquareFootageRangeShouldReturnPropertiesWithinRange() {
        int minSquareFootage = 1000;
        int maxSquareFootage = 2000;
        when(propertyRepository.findBySquareFootageBetween(minSquareFootage, maxSquareFootage))
                .thenReturn(Collections.singletonList(property));

        List<PropertyDTO> results = propertyService.findBySquareFootageRange(minSquareFootage, maxSquareFootage);

        assertThat(results).hasSize(1);
        assertThat(results.get(0).getSquareFootage()).isBetween(minSquareFootage, maxSquareFootage);
        verify(propertyRepository, times(1)).findBySquareFootageBetween(minSquareFootage, maxSquareFootage);
    }

    @Test
    void findByListingDateAfterShouldReturnPropertiesListedAfterGivenDate() {
        Date date = new Date(); // Current date
        when(propertyRepository.findByListingDateAfter(date))
                .thenReturn(Collections.singletonList(property));

        List<PropertyDTO> results = propertyService.findByListingDateAfter(date);

        assertThat(results).hasSize(1);
        assertThat(DateUtils.truncatedEquals(results.get(0).getListingDate(), date, Calendar.SECOND))
                .as("Listing date should be after or equal to the given date")
                .isTrue();
        verify(propertyRepository, times(1)).findByListingDateAfter(date);
    }


}
