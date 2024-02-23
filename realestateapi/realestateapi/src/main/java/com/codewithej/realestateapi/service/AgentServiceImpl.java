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

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AgentServiceImpl implements AgentService{

    private final AgentRepository agentRepository;

    private final PropertyRepository propertyRepository;

    private final ClientRepository clientRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public AgentServiceImpl(AgentRepository agentRepository, PropertyRepository propertyRepository, ClientRepository clientRepository, ModelMapper modelMapper) {
        this.agentRepository = agentRepository;
        this.propertyRepository = propertyRepository;
        this.clientRepository = clientRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public AgentDTO createAgent(AgentDTO agentDTO) {
        Agent agent = modelMapper.map(agentDTO, Agent.class);
        Agent savedAgent = agentRepository.save(agent);
        return modelMapper.map(savedAgent, AgentDTO.class);
    }

    @Override
    public AgentDTO updateAgent(Long id, AgentDTO agentDTO) {
        Agent agent = agentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Agent not found with id: " + id));
        modelMapper.map(agentDTO, agent);
        Agent updatedAgent = agentRepository.save(agent);
        return modelMapper.map(updatedAgent, AgentDTO.class);
    }

    @Override
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
