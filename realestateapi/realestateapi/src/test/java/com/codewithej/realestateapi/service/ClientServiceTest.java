package com.codewithej.realestateapi.service;

import com.codewithej.realestateapi.dto.ClientDTO;
import com.codewithej.realestateapi.model.Agent;
import com.codewithej.realestateapi.model.Client;
import com.codewithej.realestateapi.model.Property;
import com.codewithej.realestateapi.repository.AgentRepository;
import com.codewithej.realestateapi.repository.ClientRepository;
import com.codewithej.realestateapi.repository.PropertyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;

import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ClientServiceTest {

    @Autowired
    private ClientService clientService;

    @MockBean
    private ClientRepository clientRepository;

    @MockBean
    private AgentRepository agentRepository;

    @MockBean
    private PropertyRepository propertyRepository;

    private Client client;
    private ClientDTO clientDTO;
    private Agent agent;
    private Set<Property> interestedProperties;

    @BeforeEach
    void setUp() {
        client = new Client();
        client.setId(1L);
        client.setName("Test Client");
        client.setEmail("test@example.com");
        client.setPhoneNumber("1234567890");

        agent = new Agent();
        agent.setId(1L);
        agent.setName("Test Agent");

        interestedProperties = new HashSet<>();
        Property property = new Property();
        property.setId(1L);
        interestedProperties.add(property);

        clientDTO = new ClientDTO();
        clientDTO.setId(client.getId());
        clientDTO.setName(client.getName());
        clientDTO.setEmail(client.getEmail());
        clientDTO.setPhoneNumber(client.getPhoneNumber());

        when(clientRepository.findById(anyLong())).thenReturn(Optional.of(client));
        when(agentRepository.findById(anyLong())).thenReturn(Optional.of(agent));
    }

    @BeforeEach
    void setUpAgentAndPropertyMocks() {
        when(agentRepository.findById(anyLong())).thenReturn(Optional.of(agent));

        Property property = new Property();
        property.setId(1L); // Assuming this matches the property IDs used in tests
        when(propertyRepository.findById(anyLong())).thenReturn(Optional.of(property));
    }


    @Test
    void createClientShouldSaveClientCorrectly() {
        when(clientRepository.save(any(Client.class))).thenReturn(client);

        ClientDTO savedClient = clientService.createClient(clientDTO);

        assertThat(savedClient.getId()).isNotNull();
        verify(clientRepository).save(any(Client.class));
    }

    @Test
    void updateClientShouldUpdateClientCorrectly() {
        when(clientRepository.findById(anyLong())).thenReturn(Optional.of(client));
        when(clientRepository.save(any(Client.class))).thenReturn(client);

        ClientDTO updatedClientDTO = new ClientDTO();
        updatedClientDTO.setName("Updated Name");
        ClientDTO updatedClient = clientService.updateClient(1L, updatedClientDTO);

        assertThat(updatedClient.getName()).isEqualTo("Updated Name");
        verify(clientRepository).save(any(Client.class));
    }

    @Test
    void deleteClientShouldDeleteClientCorrectly() {
        doNothing().when(clientRepository).deleteById(anyLong());

        clientService.deleteClient(1L);

        verify(clientRepository).deleteById(anyLong());
    }

    @Test
    void getAllClientsShouldReturnListOfClients() {
        when(clientRepository.findAll()).thenReturn(Arrays.asList(client));

        List<ClientDTO> clients = clientService.getAllClients();

        assertThat(clients).isNotEmpty();
        assertThat(clients.size()).isEqualTo(1);
    }

    @Test
    void findClientsByNameShouldReturnFilteredClients() {
        when(clientRepository.findByNameContainingIgnoreCase(anyString())).thenReturn(Arrays.asList(client));

        List<ClientDTO> clients = clientService.findClientsByName("Test");

        assertThat(clients).isNotEmpty();
        assertThat(clients.size()).isEqualTo(1);
    }

    @Test
    void setClientAgentShouldAssociateAgentCorrectly() {
        // Arrange: Ensure the client and agent are properly set up and mocked
        Long clientId = 1L;
        Long agentId = 1L;
        Client expectedClient = new Client();
        expectedClient.setId(clientId);
        expectedClient.setAgent(agent);

        when(clientRepository.findById(clientId)).thenReturn(Optional.of(expectedClient));
        when(agentRepository.findById(agentId)).thenReturn(Optional.of(agent));
        when(clientRepository.save(any(Client.class))).thenReturn(expectedClient);

        // Act
        ClientDTO result = clientService.setClientAgent(clientId, agentId);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getAgent()).isNotNull();
        assertThat(result.getAgent().getId()).isEqualTo(agentId);

        // Verify the interactions
        verify(clientRepository).findById(clientId);
        verify(agentRepository).findById(agentId);
        verify(clientRepository).save(any(Client.class));
    }


    @Test
    void setInterestedPropertiesShouldUpdateInterestedPropertiesCorrectly() {
        when(clientRepository.findById(anyLong())).thenReturn(Optional.of(client));


        Set<ClientDTO> updatedClients = clientService.setInterestedProperties(client.getId(), new HashSet<>(Arrays.asList(1L)));


        assertThat(updatedClients).isNotEmpty();

    }

    @Test
    void getInterestedPropertiesShouldReturnSetOfClients() {
        when(clientRepository.findById(anyLong())).thenReturn(Optional.of(client));
        client.setIntrestedProperties(interestedProperties);

        Set<ClientDTO> resultProperties = clientService.getInterestedProperties(client.getId());

        assertThat(resultProperties).isNotEmpty();
        assertThat(resultProperties.size()).isEqualTo(interestedProperties.size());
    }

}
