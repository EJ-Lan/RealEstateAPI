package com.codewithej.realestateapi.controller;

import com.codewithej.realestateapi.dto.ClientDTO;
import com.codewithej.realestateapi.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * Rest controller for managing clients.
 * <p>
 * Provides endpoints for CRUD operations on clients, managing their associated agent,
 * and their interested properties.
 * </p>
 */
@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientService clientService;

    /**
     * Constructs a ClientController with the necessary service layer for client operations.
     *
     * @param clientService The service layer for client operations.
     */
    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    /**
     * Creates a new client.
     *
     * @param clientDTO The client DTO for creation.
     * @return ResponseEntity with the created client DTO and HTTP status code.
     */
    @PostMapping
    public ResponseEntity<ClientDTO> createClient(@RequestBody ClientDTO clientDTO) {
        ClientDTO createdClient = clientService.createClient(clientDTO);
        return new ResponseEntity<>(createdClient, HttpStatus.CREATED);
    }

    /**
     * Updates an existing client.
     *
     * @param id       The ID of the client to update.
     * @param clientDTO The updated client DTO.
     * @return ResponseEntity with the updated client DTO.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ClientDTO> updateClient(@PathVariable Long id, @RequestBody ClientDTO clientDTO) {
        ClientDTO updatedClient = clientService.updateClient(id, clientDTO);
        return ResponseEntity.ok(updatedClient);
    }

    /**
     * Deletes a client by ID.
     *
     * @param id The ID of the client to delete.
     * @return ResponseEntity with HTTP no content status.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Retrieves a client by ID.
     *
     * @param id The ID of the client to retrieve.
     * @return ResponseEntity with the found client DTO.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> getClientById(@PathVariable Long id) {
        ClientDTO client = clientService.getClientById(id);
        return ResponseEntity.ok(client);
    }

    /**
     * Retrieves all clients.
     *
     * @return ResponseEntity with the list of all client DTOs.
     */
    @GetMapping
    public ResponseEntity<List<ClientDTO>> getAllClients() {
        List<ClientDTO> clients = clientService.getAllClients();
        return ResponseEntity.ok(clients);
    }

    /**
     * Finds clients by name.
     *
     * @param name The name to search for.
     * @return ResponseEntity with the list of clients matching the name.
     */
    @GetMapping("/search")
    public ResponseEntity<List<ClientDTO>> findClientsByName(@RequestParam String name) {
        List<ClientDTO> clients = clientService.findClientsByName(name);
        return ResponseEntity.ok(clients);
    }

    /**
     * Sets the agent for a client.
     *
     * @param clientId The ID of the client.
     * @param agentId  The ID of the agent to be set for the client.
     * @return ResponseEntity with the updated client DTO.
     */
    @PostMapping("/{clientId}/setAgent/{agentId}")
    public ResponseEntity<ClientDTO> setClientAgent(@PathVariable Long clientId, @PathVariable Long agentId) {
        ClientDTO updatedClient = clientService.setClientAgent(clientId, agentId);
        return ResponseEntity.ok(updatedClient);
    }

    /**
     * Sets the properties in which a client is interested.
     *
     * @param clientId    The ID of the client.
     * @param propertyIds The IDs of the properties of interest.
     * @return ResponseEntity with the updated client DTOs.
     */
    @PostMapping("/{clientId}/interested-properties")
    public ResponseEntity<Set<ClientDTO>> setInterestedProperties(@PathVariable Long clientId, @RequestBody Set<Long> propertyIds) {
        Set<ClientDTO> updatedClients = clientService.setInterestedProperties(clientId, propertyIds);
        return new ResponseEntity<>(updatedClients, HttpStatus.OK);
    }

    /**
     * Retrieves the properties in which a client is interested.
     *
     * @param clientId The ID of the client.
     * @return ResponseEntity with the set of client DTOs interested in specific properties.
     */
    @GetMapping("/{clientId}/interested-properties")
    public ResponseEntity<Set<ClientDTO>> getInterestedProperties(@PathVariable Long clientId) {
        Set<ClientDTO> interestedProperties = clientService.getInterestedProperties(clientId);
        return ResponseEntity.ok(interestedProperties);
    }
}
