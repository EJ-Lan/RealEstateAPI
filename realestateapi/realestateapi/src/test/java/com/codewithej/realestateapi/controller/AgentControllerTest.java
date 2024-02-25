package com.codewithej.realestateapi.controller;

import com.codewithej.realestateapi.dto.AgentDTO;
import com.codewithej.realestateapi.dto.ClientDTO;
import com.codewithej.realestateapi.dto.PropertyDTO;
import com.codewithej.realestateapi.service.AgentService;
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

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AgentController.class)
@ActiveProfiles("test")
public class AgentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AgentService agentService;

    @Autowired
    private ObjectMapper objectMapper;

    private AgentDTO agentDTO;

    @BeforeEach
    void setUp() {
        agentDTO = new AgentDTO();
        agentDTO.setId(1L);
        agentDTO.setName("Agent Name");
        agentDTO.setEmail("agent@example.com");
        agentDTO.setPhoneNumber("1234567890");
    }

    @Test
    void createAgentShouldReturnCreatedAgent() throws Exception {
        given(agentService.createAgent(any(AgentDTO.class))).willReturn(agentDTO);

        mockMvc.perform(post("/api/agents")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(agentDTO)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(agentDTO.getId()));
    }

    @Test
    void updateAgentShouldReturnUpdatedAgent() throws Exception {
        given(agentService.updateAgent(anyLong(), any(AgentDTO.class))).willReturn(agentDTO);

        mockMvc.perform(put("/api/agents/{id}", agentDTO.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(agentDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(agentDTO.getId()));
    }

    @Test
    void deleteAgentShouldReturnNoContent() throws Exception {
        mockMvc.perform(delete("/api/agents/{id}", agentDTO.getId()))
                .andExpect(status().isNoContent());
    }

    @Test
    void getAgentByIdShouldReturnAgent() throws Exception {
        given(agentService.getAgentById(anyLong())).willReturn(agentDTO);

        mockMvc.perform(get("/api/agents/{id}", agentDTO.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(agentDTO.getId()));
    }

    @Test
    void getAllAgentsShouldReturnListOfAgents() throws Exception {
        List<AgentDTO> agents = Arrays.asList(agentDTO);
        given(agentService.getAllAgents()).willReturn(agents);

        mockMvc.perform(get("/api/agents"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").value(agentDTO.getId()));
    }

    @Test
    void findAgentsByNameShouldReturnFilteredAgents() throws Exception {
        List<AgentDTO> agents = Arrays.asList(agentDTO);
        given(agentService.findAgentsByName(anyString())).willReturn(agents);

        mockMvc.perform(get("/api/agents/search")
                        .param("name", "Agent"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name").value(agentDTO.getName()));
    }

    @Test
    void addManagedPropertyShouldReturnUpdatedAgent() throws Exception {
        given(agentService.addManagedProperty(anyLong(), anyLong())).willReturn(agentDTO);

        mockMvc.perform(post("/api/agents/{agentId}/properties/{propertyId}", 1L, 2L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(agentDTO.getId()));
    }

    @Test
    void addClientShouldReturnUpdatedAgent() throws Exception {
        given(agentService.addClient(anyLong(), anyLong())).willReturn(agentDTO);

        mockMvc.perform(post("/api/agents/{agentId}/clients/{clientId}", 1L, 2L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(agentDTO.getId()));
    }

    @Test
    void getClientsByAgentShouldReturnClients() throws Exception {
        List<ClientDTO> clients = Arrays.asList(new ClientDTO());
        given(agentService.getClientsByAgent(anyLong())).willReturn(clients);

        mockMvc.perform(get("/api/agents/{agentId}/clients", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    void getManagedPropertiesByAgentShouldReturnProperties() throws Exception {
        List<PropertyDTO> properties = Arrays.asList(new PropertyDTO());
        given(agentService.getManagedPropertiesByAgent(anyLong())).willReturn(properties);

        mockMvc.perform(get("/api/agents/{agentId}/properties", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }
}

