package com.codewithej.realestateapi.service;

import com.codewithej.realestateapi.dto.ClientDTO;

import java.util.List;
import java.util.Set;

/**
 * Interface for client-related operations.
 * <p>
 * Defines the contract for services handling the creation, modification, deletion,
 * and querying of client entities, including managing their associated agent and properties of interest.
 * </p>
 */
public interface ClientService {

    /**
     * Creates a new client.
     *
     * @param clientDTO The data transfer object containing the client's details.
     * @return The created client, as a DTO.
     */
    ClientDTO createClient(ClientDTO clientDTO);

    /**
     * Updates an existing client.
     *
     * @param id The ID of the client to update.
     * @param clientDTO The updated client details.
     * @return The updated client, as a DTO.
     */
    ClientDTO updateClient(Long id, ClientDTO clientDTO);

    /**
     * Deletes a client by their ID.
     *
     * @param id The ID of the client to delete.
     */
    void deleteClient(Long id);

    /**
     * Retrieves a client by their ID.
     *
     * @param id The ID of the client.
     * @return The requested client, as a DTO.
     */
    ClientDTO getClientById(Long id);

    /**
     * Retrieves all clients.
     *
     * @return A list of all clients, as DTOs.
     */
    List<ClientDTO> getAllClients();

    /**
     * Finds clients by name.
     *
     * @param name The name to search for.
     * @return A list of clients whose names contain the search term.
     */
    List<ClientDTO> findClientsByName(String name);

    /**
     * Assigns an agent to a client.
     *
     * @param clientId The ID of the client.
     * @param agentId The ID of the agent to be assigned.
     * @return The updated client, as a DTO.
     */
    ClientDTO setClientAgent(Long clientId, Long agentId);

    /**
     * Sets the properties in which a client is interested.
     *
     * @param clientId The ID of the client.
     * @param propertyIds The IDs of the properties of interest.
     * @return The updated client, as a DTO, with the new set of interested properties.
     */
    Set<ClientDTO> setInterestedProperties(Long clientId, Set<Long> propertyIds);

    /**
     * Retrieves the properties in which a client is interested.
     *
     * @param clientId The ID of the client.
     * @return A set of properties, as DTOs, in which the client is interested.
     */
    Set<ClientDTO> getInterestedProperties(Long clientId);
}
