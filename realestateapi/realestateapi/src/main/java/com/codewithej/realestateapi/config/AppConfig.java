package com.codewithej.realestateapi.config;

import com.codewithej.realestateapi.dto.PropertyDTO;
import com.codewithej.realestateapi.model.Property;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        modelMapper.typeMap(PropertyDTO.class, Property.class).addMappings(mapper -> mapper.skip(Property::setId));
        return modelMapper;
    }

}
