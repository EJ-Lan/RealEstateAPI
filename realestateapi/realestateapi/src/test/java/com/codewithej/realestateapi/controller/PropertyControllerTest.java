package com.codewithej.realestateapi.controller;

import com.codewithej.realestateapi.config.TestSecurityConfig;
import com.codewithej.realestateapi.dto.PropertyDTO;
import com.codewithej.realestateapi.model.PropertyStatus;
import com.codewithej.realestateapi.model.PropertyType;
import com.codewithej.realestateapi.service.PropertyService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PropertyController.class)
@Import(TestSecurityConfig.class)
public class PropertyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PropertyService propertyService;

    @Autowired
    private ObjectMapper objectMapper;

    private PropertyDTO propertyDTO;

    @BeforeEach
    void setUp() {
        propertyDTO = new PropertyDTO();
        propertyDTO.setId(1L);
        propertyDTO.setAddress("123 Main St");
        propertyDTO.setPrice(300000.0);
        propertyDTO.setType(PropertyType.RESIDENTIAL);
        propertyDTO.setStatus(PropertyStatus.AVAILABLE);
        propertyDTO.setBedrooms(3);
        propertyDTO.setBathrooms(2);
        propertyDTO.setSquareFootage(1500);
        propertyDTO.setYearBuilt(1990);
        propertyDTO.setListingDate(new Date());
    }

    @Test
    void createProperty_ShouldReturnCreatedProperty() throws Exception {
        given(propertyService.createProperty(any(PropertyDTO.class))).willReturn(propertyDTO);

        mockMvc.perform(post("/api/properties")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(propertyDTO)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(propertyDTO.getId()));
    }

    @Test
    void getPropertyById_ShouldReturnProperty() throws Exception {
        given(propertyService.getPropertyById(any(Long.class))).willReturn(propertyDTO);

        mockMvc.perform(get("/api/properties/{id}", propertyDTO.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(propertyDTO.getId()));
    }

    @Test
    void updateProperty_ShouldReturnUpdatedProperty() throws Exception {
        PropertyDTO updatedPropertyDTO = new PropertyDTO();
        updatedPropertyDTO.setAddress("Updated Address");
        given(propertyService.updateProperty(anyLong(), any(PropertyDTO.class))).willReturn(updatedPropertyDTO);

        mockMvc.perform(put("/api/properties/{id}", propertyDTO.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedPropertyDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.address").value("Updated Address"));
    }

    @Test
    void deleteProperty_ShouldReturnNoContent() throws Exception {
        mockMvc.perform(delete("/api/properties/{id}", propertyDTO.getId()))
                .andExpect(status().isNoContent());

        verify(propertyService, times(1)).deleteProperty(propertyDTO.getId());
    }

    @Test
    void getAllProperties_ShouldReturnListOfProperties() throws Exception {
        given(propertyService.getAllProperties()).willReturn(Collections.singletonList(propertyDTO));

        mockMvc.perform(get("/api/properties"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").value(propertyDTO.getId()));
    }

    @Test
    void findByStatus_ShouldReturnFilteredProperties() throws Exception {
        given(propertyService.findByStatus(PropertyStatus.AVAILABLE)).willReturn(Collections.singletonList(propertyDTO));

        mockMvc.perform(get("/api/properties/status/{status}", PropertyStatus.AVAILABLE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].status").value(PropertyStatus.AVAILABLE.toString()));
    }

    @Test
    void findByType_ShouldReturnFilteredProperties() throws Exception {
        given(propertyService.findByType(PropertyType.RESIDENTIAL)).willReturn(Collections.singletonList(propertyDTO));

        mockMvc.perform(get("/api/properties/type/{type}", PropertyType.RESIDENTIAL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].type").value(PropertyType.RESIDENTIAL.toString()));
    }

    @Test
    void findByBedrooms_ShouldReturnFilteredProperties() throws Exception {
        given(propertyService.findByBedrooms(3)).willReturn(Collections.singletonList(propertyDTO));

        mockMvc.perform(get("/api/properties/bedrooms/{bedrooms}", 3))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].bedrooms").value(3));
    }

    @Test
    void findByBathrooms_ShouldReturnFilteredProperties() throws Exception {
        given(propertyService.findByBathrooms(2)).willReturn(Collections.singletonList(propertyDTO));

        mockMvc.perform(get("/api/properties/bathrooms/{bathrooms}", 2))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].bathrooms").value(2));
    }

    @Test
    void findByPriceRange_ShouldReturnFilteredProperties() throws Exception {
        given(propertyService.findByPriceRange(200000.0, 300000.0)).willReturn(Collections.singletonList(propertyDTO));

        mockMvc.perform(get("/api/properties/price")
                        .param("minPrice", "200000")
                        .param("maxPrice", "300000"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].price").value(propertyDTO.getPrice()));
    }

    @Test
    void findBySquareFootageRange_ShouldReturnFilteredProperties() throws Exception {
        given(propertyService.findBySquareFootageRange(1000, 2000)).willReturn(Collections.singletonList(propertyDTO));

        mockMvc.perform(get("/api/properties/squareFootage")
                        .param("minSquareFootage", "1000")
                        .param("maxSquareFootage", "2000"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].squareFootage").value(propertyDTO.getSquareFootage()));
    }

    @Test
    void findByListingDateAfter_ShouldReturnFilteredProperties() throws Exception {
        Date testDate = new SimpleDateFormat("yyyy-MM-dd").parse("2023-01-01");
        PropertyDTO testPropertyDTO = new PropertyDTO();
        List<PropertyDTO> testProperties = Collections.singletonList(testPropertyDTO);

        when(propertyService.findByListingDateAfter(any(Date.class))).thenReturn(testProperties);

        mockMvc.perform(get("/api/properties/listingDateAfter")
                        .param("date", "2023-01-01")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));

        verify(propertyService).findByListingDateAfter(refEq(testDate));
    }


}
