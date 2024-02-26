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
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of the {@link AgentService} interface.
 * <p>
 * Handles business logic for agent operations, including CRUD functionalities and relationships
 * with properties and clients. Utilizes {@link AgentRepository}, {@link PropertyRepository},
 * and {@link ClientRepository} for data access.
 * </p>
 */
@Service
public class AgentServiceImpl implements AgentService {

    private final AgentRepository agentRepository;

    private final PropertyRepository propertyRepository;

    private final ClientRepository clientRepository;

    private final ModelMapper modelMapper;

    /**
     * Constructs an {@code AgentServiceImpl} with necessary dependencies.
     *
     * @param agentRepository     Repository for agent data access.
     * @param propertyRepository  Repository for property data access.
     * @param clientRepository    Repository for client data access.
     * @param modelMapper         Utility for entity-DTO mapping.
     */
    @Autowired
    public AgentServiceImpl(AgentRepository agentRepository, PropertyRepository propertyRepository, ClientRepository clientRepository, ModelMapper modelMapper) {
        this.agentRepository = agentRepository;
        this.propertyRepository = propertyRepository;
        this.clientRepository = clientRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public AgentDTO createAgent(AgentDTO agentDTO) {
        Agent agent = modelMapper.map(agentDTO, Agent.class);
        Agent savedAgent = agentRepository.save(agent);
        return modelMapper.map(savedAgent, AgentDTO.class);
    }

    @Override
    @Transactional
    public AgentDTO updateAgent(Long id, AgentDTO agentDTO) {
        Agent agent = agentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Agent not found with id: " + id));
        modelMapper.map(agentDTO, agent);
        Agent updatedAgent = agentRepository.save(agent);
        return modelMapper.map(updatedAgent, AgentDTO.class);
    }

    @Override
    @Transactional
    public void deleteAgent(Long id) {
        agentRepository.deleteById(id);
    }

    @Override
    public AgentDTO getAgentById(Long id) {
        Agent agent = agentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Agent not found with id: " + id));
        return modelMapper.map(agent, AgentDTO.class);
    }

    @Override
    public List<AgentDTO> getAllAgents() {
        return agentRepository.findAll().stream()
                .map(agent -> modelMapper.map(agent, AgentDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<AgentDTO> findAgentsByName(String name) {
        return agentRepository.findByNameContainingIgnoreCase(name).stream()
                .map(agent -> modelMapper.map(agent, AgentDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public AgentDTO addManagedProperty(Long agentId, Long propertyId) {
        Agent agent = agentRepository.findById(agentId)
                .orElseThrow(() -> new RuntimeException("Agent not found with id: " + agentId));
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new RuntimeException("Property not found with id: " + propertyId));
        agent.getManagedProperties().add(property);
        Agent updatedAgent = agentRepository.save(agent);
        return modelMapper.map(updatedAgent, AgentDTO.class);
    }

    @Override
    @Transactional
    public AgentDTO addClient(Long agentId, Long clientId) {
        Agent agent = agentRepository.findById(agentId)
                .orElseThrow(() -> new RuntimeException("Agent not found with id: " + agentId));
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found with id: " + clientId));
        agent.getClients().add(client);
        Agent updatedAgent = agentRepository.save(agent);
        return modelMapper.map(updatedAgent, AgentDTO.class);
    }

    @Override
    public List<ClientDTO> getClientsByAgent(Long agentId) {
        Agent agent = agentRepository.findById(agentId)
                .orElseThrow(() -> new RuntimeException("Agent not found with id: " + agentId));
        return agent.getClients().stream()
                .map(client -> modelMapper.map(client, ClientDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PropertyDTO> getManagedPropertiesByAgent(Long agentId) {
        Agent agent = agentRepository.findById(agentId)
                .orElseThrow(() -> new RuntimeException("Agent not found with id: " + agentId));
        return agent.getManagedProperties().stream()
                .map(property -> modelMapper.map(property, PropertyDTO.class))
                .collect(Collectors.toList());
    }

}
