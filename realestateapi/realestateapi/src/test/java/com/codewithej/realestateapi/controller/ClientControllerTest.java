package com.codewithej.realestateapi.controller;

import com.codewithej.realestateapi.dto.ClientDTO;
import com.codewithej.realestateapi.service.ClientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ClientController.class)
@ActiveProfiles("test")
public class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientService clientService;

    @Autowired
    private ObjectMapper objectMapper;

    private ClientDTO clientDTO;

    @BeforeEach
    void setUp() {
        clientDTO = new ClientDTO();
        clientDTO.setId(1L);
        clientDTO.setName("John Doe");
        clientDTO.setEmail("john.doe@example.com");
        clientDTO.setPhoneNumber("1234567890");
        clientDTO.setPreferences("Likes large kitchens");
    }

    @Test
    void createClientShouldReturnCreatedClient() throws Exception {
        given(clientService.createClient(any(ClientDTO.class))).willReturn(clientDTO);

        mockMvc.perform(post("/api/clients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clientDTO)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(clientDTO.getId()));
    }

    @Test
    void updateClientShouldReturnUpdatedClient() throws Exception {
        given(clientService.updateClient(anyLong(), any(ClientDTO.class))).willReturn(clientDTO);

        mockMvc.perform(put("/api/clients/{id}", clientDTO.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clientDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(clientDTO.getId()));
    }

    @Test
    void deleteClientShouldReturnNoContent() throws Exception {
        mockMvc.perform(delete("/api/clients/{id}", clientDTO.getId()))
                .andExpect(status().isNoContent());

        verify(clientService, times(1)).deleteClient(clientDTO.getId());
    }

    @Test
    void getClientByIdShouldReturnClient() throws Exception {
        given(clientService.getClientById(anyLong())).willReturn(clientDTO);

        mockMvc.perform(get("/api/clients/{id}", clientDTO.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(clientDTO.getId()));
    }

    @Test
    void getAllClientsShouldReturnListOfClients() throws Exception {
        List<ClientDTO> clients = Collections.singletonList(clientDTO);
        given(clientService.getAllClients()).willReturn(clients);

        mockMvc.perform(get("/api/clients"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").value(clientDTO.getId()));
    }

    @Test
    void findClientsByNameShouldReturnFilteredClients() throws Exception {
        List<ClientDTO> clients = Collections.singletonList(clientDTO);
        given(clientService.findClientsByName(anyString())).willReturn(clients);

        mockMvc.perform(get("/api/clients/search")
                        .param("name", "John"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name").value(clientDTO.getName()));
    }

    @Test
    void setClientAgentShouldAssociateAgentCorrectly() throws Exception {
        given(clientService.setClientAgent(anyLong(), anyLong())).willReturn(clientDTO);

        mockMvc.perform(post("/api/clients/{clientId}/setAgent/{agentId}", 1L, 2L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(clientDTO.getId()));
    }

    @Test
    void setInterestedPropertiesShouldUpdateInterestedPropertiesCorrectly() throws Exception {
        Set<ClientDTO> updatedClients = new HashSet<>(Collections.singletonList(clientDTO));
        given(clientService.setInterestedProperties(anyLong(), any(Set.class))).willReturn(updatedClients);

        mockMvc.perform(post("/api/clients/{clientId}/interested-properties", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(Collections.singleton(1L))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").value(clientDTO.getId()));
    }

    @Test
    void getInterestedPropertiesShouldReturnSetOfInterestedProperties() throws Exception {
        Set<ClientDTO> interestedProperties = new HashSet<>(Collections.singletonList(clientDTO));
        given(clientService.getInterestedProperties(anyLong())).willReturn(interestedProperties);

        mockMvc.perform(get("/api/clients/{clientId}/interested-properties", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").value(clientDTO.getId()));
    }
}
