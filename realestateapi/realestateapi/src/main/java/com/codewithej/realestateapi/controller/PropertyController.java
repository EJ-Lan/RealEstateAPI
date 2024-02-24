package com.codewithej.realestateapi.controller;

import com.codewithej.realestateapi.dto.PropertyDTO;
import com.codewithej.realestateapi.model.PropertyStatus;
import com.codewithej.realestateapi.model.PropertyType;
import com.codewithej.realestateapi.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/properties")
public class PropertyController {

    private final PropertyService propertyService;

    @Autowired
    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @PostMapping
    public ResponseEntity<PropertyDTO> createProperty(@RequestBody PropertyDTO propertyDTO) {
        PropertyDTO createdProperty = propertyService.createProperty(propertyDTO);
        return new ResponseEntity<>(createdProperty, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PropertyDTO> getPropertyById(@PathVariable Long id) {
        PropertyDTO propertyDTO= propertyService.getPropertyById(id);
        return ResponseEntity.ok(propertyDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PropertyDTO> updateProperty(@PathVariable Long id, @RequestBody PropertyDTO propertyDTO) {
        PropertyDTO updatedProperty = propertyService.updateProperty(id, propertyDTO);
        return ResponseEntity.ok(updatedProperty);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProperty(@PathVariable Long id) {
        propertyService.deleteProperty(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<PropertyDTO>> getAllProperties() {
        List<PropertyDTO> properties = propertyService.getAllProperties();
        return ResponseEntity.ok(properties);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<PropertyDTO>> findByStatus(@PathVariable PropertyStatus status) {
        List<PropertyDTO> properties = propertyService.findByStatus(status);
        return ResponseEntity.ok(properties);
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<PropertyDTO>> findByType(@PathVariable PropertyType type) {
        List<PropertyDTO> properties = propertyService.findByType(type);
        return ResponseEntity.ok(properties);
    }

    @GetMapping("/bedrooms/{bedrooms}")
    public ResponseEntity<List<PropertyDTO>> findByBedrooms(@PathVariable Integer bedrooms) {
        List<PropertyDTO> properties = propertyService.findByBedrooms(bedrooms);
        return ResponseEntity.ok(properties);
    }

    @GetMapping("/bathrooms/{bathrooms}")
    public ResponseEntity<List<PropertyDTO>> findByBathrooms(@PathVariable Integer bathrooms) {
        List<PropertyDTO> properties = propertyService.findByBathrooms(bathrooms);
        return ResponseEntity.ok(properties);
    }

    @GetMapping("/price")
    public ResponseEntity<List<PropertyDTO>> findByPriceRange(@RequestParam Double minPrice, @RequestParam Double maxPrice) {
        List<PropertyDTO> properties = propertyService.findByPriceRange(minPrice, maxPrice);
        return ResponseEntity.ok(properties);
    }

    @GetMapping("/squareFootage")
    public ResponseEntity<List<PropertyDTO>> findBySquareFootageRange(@RequestParam Integer minSquareFootage, @RequestParam Integer maxSquareFootage) {
        List<PropertyDTO> properties = propertyService.findBySquareFootageRange(minSquareFootage, maxSquareFootage);
        return ResponseEntity.ok(properties);
    }

    @GetMapping("/listingDateAfter")
    public ResponseEntity<List<PropertyDTO>> findByListingDateAfter(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date) {
        List<PropertyDTO> properties = propertyService.findByListingDateAfter(date);
        return ResponseEntity.ok(properties);
    }
}
