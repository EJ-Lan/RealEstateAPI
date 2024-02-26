package com.codewithej.realestateapi.service;


import com.codewithej.realestateapi.dto.AgentDTO;
import com.codewithej.realestateapi.dto.ClientDTO;
import com.codewithej.realestateapi.dto.PropertyDTO;

import java.util.List;

/**
 * Interface for agent-related operations.
 * <p>
 * Defines the contract for services handling the creation, modification, deletion,
 * and querying of agent entities, including managing their properties and clients.
 * </p>
 */
public interface AgentService {

    /**
     * Creates a new agent.
     *
     * @param agentDTO The data transfer object containing the agent's details.
     * @return The created agent, as a DTO.
     */
    AgentDTO createAgent(AgentDTO agentDTO);

    /**
     * Updates an existing agent.
     *
     * @param id The ID of the agent to update.
     * @param agentDTO The updated agent details.
     * @return The updated agent, as a DTO.
     */
    AgentDTO updateAgent(Long id, AgentDTO agentDTO);

    /**
     * Deletes an agent by their ID.
     *
     * @param id The ID of the agent to delete.
     */
    void deleteAgent(Long id);

    /**
     * Retrieves an agent by their ID.
     *
     * @param id The ID of the agent.
     * @return The requested agent, as a DTO.
     */
    AgentDTO getAgentById(Long id);

    /**
     * Retrieves all agents.
     *
     * @return A list of all agents, as DTOs.
     */
    List<AgentDTO> getAllAgents();

    /**
     * Finds agents by name.
     *
     * @param name The name to search for.
     * @return A list of agents whose names contain the search term.
     */
    List<AgentDTO> findAgentsByName(String name);

    /**
     * Adds a property to an agent's list of managed properties.
     *
     * @param agentId The ID of the agent.
     * @param propertyId The ID of the property to add.
     * @return The updated agent, as a DTO.
     */
    AgentDTO addManagedProperty(Long agentId, Long propertyId);

    /**
     * Adds a client to an agent's list of clients.
     *
     * @param agentId The ID of the agent.
     * @param clientId The ID of the client to add.
     * @return The updated agent, as a DTO.
     */
    AgentDTO addClient(Long agentId, Long clientId);

    /**
     * Retrieves all clients associated with a specific agent.
     *
     * @param agentId The ID of the agent.
     * @return A list of clients, as DTOs, managed by the agent.
     */
    List<ClientDTO> getClientsByAgent(Long agentId);

    /**
     * Retrieves all properties managed by a specific agent.
     *
     * @param agentId The ID of the agent.
     * @return A list of properties, as DTOs, managed by the agent.
     */
    List<PropertyDTO> getManagedPropertiesByAgent(Long agentId);
}

