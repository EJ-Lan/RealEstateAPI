package com.codewithej.realestateapi.repository;

import com.codewithej.realestateapi.model.Property;
import com.codewithej.realestateapi.model.PropertyStatus;
import com.codewithej.realestateapi.model.PropertyType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class PropertyRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PropertyRepository propertyRepository;

    @BeforeEach
    public void setup() {
        Property property = new Property();
        property.setAddress("123 Main St");
        property.setStatus(PropertyStatus.AVAILABLE);
        property.setType(PropertyType.RESIDENTIAL);
        property.setPrice(200000.0);
        property.setBedrooms(3);
        property.setBathrooms(2);
        property.setSquareFootage(1200);
        property.setListingDate(new Date());

        entityManager.persist(property);
    }

    @Test
    public void shouldFindPropertiesByStatus() {
        List<Property> result = propertyRepository.findByStatus(PropertyStatus.AVAILABLE);
        assertFalse(result.isEmpty());
        assertEquals(PropertyStatus.AVAILABLE, result.get(0).getStatus());
    }

    @Test
    public void shouldFindPropertiesByType() {
        List<Property> result = propertyRepository.findByType(PropertyType.RESIDENTIAL);
        assertFalse(result.isEmpty());
        assertEquals(PropertyType.RESIDENTIAL, result.get(0).getType());
    }

    @Test
    public void shouldFindPropertiesWithinPriceRange() {
        List<Property> result = propertyRepository.findByPriceBetween(150000.0, 250000.0);
        assertFalse(result.isEmpty());
        assertTrue(result.get(0).getPrice() >= 150000.0 && result.get(0).getPrice() <= 250000.0);
    }

    @Test
    public void shouldFindPropertiesByNumberOfBedrooms() {
        List<Property> result = propertyRepository.findByBedrooms(3);
        assertFalse(result.isEmpty());
        assertEquals(3, result.get(0).getBedrooms());
    }

    @Test
    public void shouldFindPropertiesByNumberOfBathrooms() {
        List<Property> result = propertyRepository.findByBathrooms(2);
        assertFalse(result.isEmpty());
        assertEquals(2, result.get(0).getBathrooms());
    }

    @Test
    public void shouldFindPropertiesWithinSquareFootageRange() {
        List<Property> result = propertyRepository.findBySquareFootageBetween(1000, 1300);
        assertFalse(result.isEmpty());
        assertTrue(result.get(0).getSquareFootage() >= 1000 && result.get(0).getSquareFootage() <= 1300);
    }

    @Test
    public void shouldFindPropertiesListedAfterSpecificDate() {
        Date oneDayBefore = new Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000); // 1 day before now
        List<Property> result = propertyRepository.findByListingDateAfter(oneDayBefore);
        assertFalse(result.isEmpty());
        assertTrue(result.get(0).getListingDate().after(oneDayBefore));
    }


}
