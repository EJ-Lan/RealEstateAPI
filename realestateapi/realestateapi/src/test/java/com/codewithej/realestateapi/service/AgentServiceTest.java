package com.codewithej.realestateapi.service;

import com.codewithej.realestateapi.dto.AgentDTO;
import com.codewithej.realestateapi.dto.ClientDTO;
import com.codewithej.realestateapi.dto.PropertyDTO;
import com.codewithej.realestateapi.model.Agent;
import com.codewithej.realestateapi.model.Client;
import com.codewithej.realestateapi.model.Property;
import com.codewithej.realestateapi.repository.AgentRepository;
import com.codewithej.realestateapi.repository.ClientRepository;
import com.codewithej.realestateapi.repository.PropertyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class AgentServiceTest {

    @Autowired
    private AgentService agentService;

    @MockBean
    private AgentRepository agentRepository;

    @MockBean
    private PropertyRepository propertyRepository;

    @MockBean
    private ClientRepository clientRepository;

    @MockBean
    private ModelMapper modelMapper;

    private Agent agent;
    private AgentDTO agentDTO;

    @BeforeEach
    void setUp() {
        agent = new Agent();
        agent.setId(1L);
        agent.setName("John Doe");
        agent.setEmail("john.doe@example.com");
        agent.setPhoneNumber("1234567890");

        agentDTO = new AgentDTO();
        agentDTO.setId(agent.getId());
        agentDTO.setName(agent.getName());
        agentDTO.setEmail(agent.getEmail());
        agentDTO.setPhoneNumber(agent.getPhoneNumber());

        when(modelMapper.map(any(Agent.class), eq(AgentDTO.class))).thenReturn(agentDTO);
        when(modelMapper.map(any(AgentDTO.class), eq(Agent.class))).thenReturn(agent);
    }

    @Test
    void createAgentShouldSaveAgentCorrectly() {
        when(agentRepository.save(any(Agent.class))).thenReturn(agent);

        AgentDTO savedAgent = agentService.createAgent(agentDTO);

        assertThat(savedAgent.getId()).isEqualTo(agent.getId());
        verify(agentRepository, times(1)).save(any(Agent.class));
    }

    @Test
    void updateAgentShouldUpdateAgentCorrectly() {
        when(agentRepository.findById(anyLong())).thenReturn(Optional.of(agent));
        when(agentRepository.save(any(Agent.class))).thenReturn(agent);

        AgentDTO updatedAgent = agentService.updateAgent(1L, agentDTO);

        assertThat(updatedAgent.getName()).isEqualTo(agent.getName());
        verify(agentRepository, times(1)).save(any(Agent.class));
    }

    @Test
    void deleteAgentShouldDeleteAgentCorrectly() {
        doNothing().when(agentRepository).deleteById(anyLong());

        agentService.deleteAgent(1L);

        verify(agentRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void getAgentByIdShouldReturnAgent() {
        when(agentRepository.findById(anyLong())).thenReturn(Optional.of(agent));

        AgentDTO foundAgent = agentService.getAgentById(1L);

        assertThat(foundAgent.getId()).isEqualTo(agent.getId());
        verify(agentRepository, times(1)).findById(anyLong());
    }

    @Test
    void getAllAgentsShouldReturnListOfAgents() {
        when(agentRepository.findAll()).thenReturn(Arrays.asList(agent));

        List<AgentDTO> agents = agentService.getAllAgents();

        assertThat(agents).hasSize(1);
        assertThat(agents.get(0).getId()).isEqualTo(agent.getId());
    }

    @Test
    void findAgentsByNameShouldReturnFilteredAgents() {
        when(agentRepository.findByNameContainingIgnoreCase(anyString())).thenReturn(Arrays.asList(agent));

        List<AgentDTO> agents = agentService.findAgentsByName("John");

        assertThat(agents).isNotEmpty();
        assertThat(agents.get(0).getName()).isEqualTo(agent.getName());
    }

    @Test
    void addManagedPropertyShouldAssociatePropertyCorrectly() {
        Property property = new Property();
        property.setId(2L);
        when(propertyRepository.findById(anyLong())).thenReturn(Optional.of(property));
        when(agentRepository.save(any(Agent.class))).thenReturn(agent);
        when(agentRepository.findById(anyLong())).thenReturn(Optional.of(agent));

        AgentDTO updatedAgent = agentService.addManagedProperty(agent.getId(), property.getId());

        assertThat(updatedAgent).isNotNull();
        verify(agentRepository, times(1)).save(any(Agent.class));
        verify(propertyRepository, times(1)).findById(anyLong());
    }

    @Test
    void addClientShouldAssociateClientCorrectly() {
        Client client = new Client();
        client.setId(3L);
        when(clientRepository.findById(anyLong())).thenReturn(Optional.of(client));
        when(agentRepository.save(any(Agent.class))).thenReturn(agent);
        when(agentRepository.findById(anyLong())).thenReturn(Optional.of(agent));

        AgentDTO updatedAgent = agentService.addClient(agent.getId(), client.getId());

        assertThat(updatedAgent).isNotNull();
        verify(agentRepository, times(1)).save(any(Agent.class));
        verify(clientRepository, times(1)).findById(anyLong());
    }

    @Test
    void getClientsByAgentShouldReturnListOfClients() {
        Client client = new Client();
        client.setId(3L);
        agent.setClients(new HashSet<>(Arrays.asList(client)));
        when(agentRepository.findById(anyLong())).thenReturn(Optional.of(agent));
        when(modelMapper.map(any(Client.class), eq(ClientDTO.class))).thenReturn(new ClientDTO());

        List<ClientDTO> clients = agentService.getClientsByAgent(agent.getId());

        assertThat(clients).isNotEmpty();
        assertThat(clients.size()).isEqualTo(1);
        verify(agentRepository, times(1)).findById(anyLong());
    }

    @Test
    void getManagedPropertiesByAgentShouldReturnListOfProperties() {
        Property property = new Property();
        property.setId(2L);
        agent.setManagedProperties(new HashSet<>(Arrays.asList(property)));
        when(agentRepository.findById(anyLong())).thenReturn(Optional.of(agent));
        when(modelMapper.map(any(Property.class), eq(PropertyDTO.class))).thenReturn(new PropertyDTO());

        List<PropertyDTO> properties = agentService.getManagedPropertiesByAgent(agent.getId());

        assertThat(properties).isNotEmpty();
        assertThat(properties.size()).isEqualTo(1);
        verify(agentRepository, times(1)).findById(anyLong());
    }


}
