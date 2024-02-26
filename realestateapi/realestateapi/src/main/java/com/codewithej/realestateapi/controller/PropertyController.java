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

/**
 * Rest controller for managing properties.
 * <p>
 * Provides endpoints for CRUD operations on properties, as well as searching properties
 * by various criteria such as status, type, number of bedrooms/bathrooms, price range, square footage, and listing date.
 * </p>
 */
@RestController
@RequestMapping("/api/properties")
public class PropertyController {

    private final PropertyService propertyService;

    /**
     * Constructs a PropertyController with the necessary service layer for property operations.
     *
     * @param propertyService The service layer for property operations.
     */
    @Autowired
    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    /**
     * Creates a new property.
     *
     * @param propertyDTO The property DTO for creation.
     * @return ResponseEntity with the created property DTO and HTTP status code.
     */
    @PostMapping
    public ResponseEntity<PropertyDTO> createProperty(@RequestBody PropertyDTO propertyDTO) {
        PropertyDTO createdProperty = propertyService.createProperty(propertyDTO);
        return new ResponseEntity<>(createdProperty, HttpStatus.CREATED);
    }

    /**
     * Retrieves a property by ID.
     *
     * @param id The ID of the property to retrieve.
     * @return ResponseEntity with the found property DTO.
     */
    @GetMapping("/{id}")
    public ResponseEntity<PropertyDTO> getPropertyById(@PathVariable Long id) {
        PropertyDTO propertyDTO= propertyService.getPropertyById(id);
        return ResponseEntity.ok(propertyDTO);
    }

    /**
     * Updates an existing property.
     *
     * @param id         The ID of the property to update.
     * @param propertyDTO The updated property DTO.
     * @return ResponseEntity with the updated property DTO.
     */
    @PutMapping("/{id}")
    public ResponseEntity<PropertyDTO> updateProperty(@PathVariable Long id, @RequestBody PropertyDTO propertyDTO) {
        PropertyDTO updatedProperty = propertyService.updateProperty(id, propertyDTO);
        return ResponseEntity.ok(updatedProperty);
    }

    /**
     * Deletes a property by ID.
     *
     * @param id The ID of the property to delete.
     * @return ResponseEntity with HTTP no content status.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProperty(@PathVariable Long id) {
        propertyService.deleteProperty(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Retrieves all properties.
     *
     * @return ResponseEntity with the list of all property DTOs.
     */
    @GetMapping
    public ResponseEntity<List<PropertyDTO>> getAllProperties() {
        List<PropertyDTO> properties = propertyService.getAllProperties();
        return ResponseEntity.ok(properties);
    }

    /**
     * Finds properties by status.
     *
     * @param status The status to search for.
     * @return ResponseEntity with the list of properties matching the status.
     */
    @GetMapping("/status/{status}")
    public ResponseEntity<List<PropertyDTO>> findByStatus(@PathVariable PropertyStatus status) {
        List<PropertyDTO> properties = propertyService.findByStatus(status);
        return ResponseEntity.ok(properties);
    }

    /**
     * Finds properties by type.
     *
     * @param type The type to search for.
     * @return ResponseEntity with the list of properties matching the type.
     */
    @GetMapping("/type/{type}")
    public ResponseEntity<List<PropertyDTO>> findByType(@PathVariable PropertyType type) {
        List<PropertyDTO> properties = propertyService.findByType(type);
        return ResponseEntity.ok(properties);
    }

    /**
     * Finds properties by the number of bedrooms.
     *
     * @param bedrooms The number of bedrooms to search for.
     * @return ResponseEntity with the list of properties matching the number of bedrooms.
     */
    @GetMapping("/bedrooms/{bedrooms}")
    public ResponseEntity<List<PropertyDTO>> findByBedrooms(@PathVariable Integer bedrooms) {
        List<PropertyDTO> properties = propertyService.findByBedrooms(bedrooms);
        return ResponseEntity.ok(properties);
    }

    /**
     * Finds properties by the number of bathrooms.
     *
     * @param bathrooms The number of bathrooms to search for.
     * @return ResponseEntity with the list of properties matching the number of bathrooms.
     */
    @GetMapping("/bathrooms/{bathrooms}")
    public ResponseEntity<List<PropertyDTO>> findByBathrooms(@PathVariable Integer bathrooms) {
        List<PropertyDTO> properties = propertyService.findByBathrooms(bathrooms);
        return ResponseEntity.ok(properties);
    }

    /**
     * Finds properties within a specified price range.
     *
     * @param minPrice The minimum price.
     * @param maxPrice The maximum price.
     * @return ResponseEntity with the list of properties within the price range.
     */
    @GetMapping("/price")
    public ResponseEntity<List<PropertyDTO>> findByPriceRange(@RequestParam Double minPrice, @RequestParam Double maxPrice) {
        List<PropertyDTO> properties = propertyService.findByPriceRange(minPrice, maxPrice);
        return ResponseEntity.ok(properties);
    }

    /**
     * Finds properties within a specified square footage range.
     *
     * @param minSquareFootage The minimum square footage.
     * @param maxSquareFootage The maximum square footage.
     * @return ResponseEntity with the list of properties within the square footage range.
     */
    @GetMapping("/squareFootage")
    public ResponseEntity<List<PropertyDTO>> findBySquareFootageRange(@RequestParam Integer minSquareFootage, @RequestParam Integer maxSquareFootage) {
        List<PropertyDTO> properties = propertyService.findBySquareFootageRange(minSquareFootage, maxSquareFootage);
        return ResponseEntity.ok(properties);
    }

    /**
     * Finds properties listed after a specified date.
     *
     * @param date The date after which properties are listed.
     * @return ResponseEntity with the list of properties listed after the specified date.
     */
    @GetMapping("/listingDateAfter")
    public ResponseEntity<List<PropertyDTO>> findByListingDateAfter(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date) {
        List<PropertyDTO> properties = propertyService.findByListingDateAfter(date);
        return ResponseEntity.ok(properties);
    }
}
