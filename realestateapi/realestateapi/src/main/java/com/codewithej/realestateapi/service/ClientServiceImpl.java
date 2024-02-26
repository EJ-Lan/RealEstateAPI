package com.codewithej.realestateapi.service;

import com.codewithej.realestateapi.dto.ClientDTO;
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
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Implementation of the {@link ClientService} interface.
 * <p>
 * Handles business logic for client operations, including CRUD functionalities and relationships
 * with agents and properties. Utilizes {@link ClientRepository}, {@link AgentRepository},
 * and {@link PropertyRepository} for data access.
 * </p>
 */
@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    private final AgentRepository agentRepository;

    private final PropertyRepository propertyRepository;

    private final ModelMapper modelMapper;

    /**
     * Constructs a {@code ClientServiceImpl} with necessary dependencies.
     *
     * @param clientRepository    Repository for client data access.
     * @param agentRepository     Repository for agent data access.
     * @param propertyRepository  Repository for property data access.
     * @param modelMapper         Utility for entity-DTO mapping.
     */
    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository, AgentRepository agentRepository, PropertyRepository propertyRepository, ModelMapper modelMapper) {
        this.clientRepository = clientRepository;
        this.agentRepository = agentRepository;
        this.propertyRepository = propertyRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public ClientDTO createClient(ClientDTO clientDTO) {
        Client client = modelMapper.map(clientDTO, Client.class);
        Client savedClient = clientRepository.save(client);
        return modelMapper.map(savedClient, ClientDTO.class);
    }

    @Override
    @Transactional
    public ClientDTO updateClient(Long id, ClientDTO clientDTO) {
        Client existingClient = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found with id: " + id));
        modelMapper.map(clientDTO, existingClient);
        Client updatedClient = clientRepository.save(existingClient);
        return modelMapper.map(updatedClient, ClientDTO.class);
    }

    @Override
    @Transactional
    public void deleteClient(Long id) {
        clientRepository.deleteById(id);

    }

    @Override
    public ClientDTO getClientById(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found with id: " + id));
        return modelMapper.map(client, ClientDTO.class);
    }

    @Override
    public List<ClientDTO> getAllClients() {
        List<Client> clients = clientRepository.findAll();
        return clients.stream()
                .map(client -> modelMapper.map(client, ClientDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ClientDTO> findClientsByName(String name) {
        List<Client> clients = clientRepository.findByNameContainingIgnoreCase(name);
        return clients.stream()
                .map(client -> modelMapper.map(client, ClientDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ClientDTO setClientAgent(Long clientId, Long agentId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found with id: " + clientId));
        Agent agent = agentRepository.findById(agentId)
                .orElseThrow(() -> new RuntimeException("Agent not found with id: " + agentId));
        client.setAgent(agent);
        Client updatedClient = clientRepository.save(client);

        if (updatedClient != null) {
            return modelMapper.map(updatedClient, ClientDTO.class);
        } else {
            throw new RuntimeException("Failed to update client with id: " + clientId);
        }
    }

    @Override
    @Transactional
    public Set<ClientDTO> setInterestedProperties(Long clientId, Set<Long> propertyIds) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found with id: " + clientId));
        Set<Property> properties = propertyIds.stream()
                .map(id -> propertyRepository.findById(id).orElseThrow(() -> new RuntimeException("Property not found with id: " + id)))
                .collect(Collectors.toSet());
        client.setInterestedProperties(properties);
        clientRepository.save(client);
        return properties.stream()
                .map(property -> modelMapper.map(property, ClientDTO.class))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<ClientDTO> getInterestedProperties(Long clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found with id: " + clientId));
        Set<Property> interestedProperties = client.getInterestedProperties();
        return interestedProperties.stream()
                .map(property -> modelMapper.map(property, ClientDTO.class))
                .collect(Collectors.toSet());
    }
}
