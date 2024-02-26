package com.codewithej.realestateapi.config;

import com.codewithej.realestateapi.dto.PropertyDTO;
import com.codewithej.realestateapi.model.Property;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for application-wide beans.
 * <p>
 * This class is responsible for setting up model mapping configurations that are
 * used throughout the application, specifically for DTO to entity mappings and vice versa.
 * </p>
 */
@Configuration
public class AppConfig {

    /**
     * Configures and returns a ModelMapper bean to be used throughout the application.
     * The ModelMapper is configured to ignore ambiguities and skip setting the ID when mapping
     * from PropertyDTO to Property, ensuring data integrity during the mapping process.
     *
     * @return The configured ModelMapper instance.
     */
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        modelMapper.typeMap(PropertyDTO.class, Property.class).addMappings(mapper -> mapper.skip(Property::setId));
        return modelMapper;
    }

}
