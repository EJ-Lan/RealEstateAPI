package com.codewithej.realestateapi.controller;

import com.codewithej.realestateapi.dto.AgentDTO;
import com.codewithej.realestateapi.dto.ClientDTO;
import com.codewithej.realestateapi.dto.PropertyDTO;
import com.codewithej.realestateapi.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Rest controller for managing agents.
 * <p>
 * Provides endpoints for CRUD operations on agents, managing their properties and clients.
 * </p>
 */
@RestController
@RequestMapping("/api/agents")
public class AgentController {

    private final AgentService agentService;

    /**
     * Constructs an AgentController with the necessary service layer.
     *
     * @param agentService The service layer for agent operations.
     */
    @Autowired
    public AgentController(AgentService agentService) {
        this.agentService = agentService;
    }

    /**
     * Creates a new agent.
     *
     * @param agentDTO The agent DTO for creation.
     * @return ResponseEntity with the created agent DTO and HTTP status code.
     */
    @PostMapping
    public ResponseEntity<AgentDTO> createAgent(@RequestBody AgentDTO agentDTO) {
        AgentDTO createdAgent = agentService.createAgent(agentDTO);
        return new ResponseEntity<>(createdAgent, HttpStatus.CREATED);
    }

    /**
     * Updates an existing agent.
     *
     * @param id       The ID of the agent to update.
     * @param agentDTO The updated agent DTO.
     * @return ResponseEntity with the updated agent DTO.
     */
    @PutMapping("/{id}")
    public ResponseEntity<AgentDTO> updateAgent(@PathVariable Long id, @RequestBody AgentDTO agentDTO) {
        AgentDTO updatedAgent = agentService.updateAgent(id, agentDTO);
        return ResponseEntity.ok(updatedAgent);
    }

    /**
     * Deletes an agent by ID.
     *
     * @param id The ID of the agent to delete.
     * @return ResponseEntity with HTTP no content status.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAgent(@PathVariable Long id) {
        agentService.deleteAgent(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Retrieves an agent by ID.
     *
     * @param id The ID of the agent to retrieve.
     * @return ResponseEntity with the found agent DTO.
     */
    @GetMapping("/{id}")
    public ResponseEntity<AgentDTO> getAgentById(@PathVariable Long id) {
        AgentDTO agent = agentService.getAgentById(id);
        return ResponseEntity.ok(agent);
    }

    /**
     * Retrieves all agents.
     *
     * @return ResponseEntity with the list of all agent DTOs.
     */
    @GetMapping
    public ResponseEntity<List<AgentDTO>> getAllAgents() {
        List<AgentDTO> agents = agentService.getAllAgents();
        return ResponseEntity.ok(agents);
    }

    /**
     * Finds agents by name.
     *
     * @param name The name to search for.
     * @return ResponseEntity with the list of agents matching the name.
     */
    @GetMapping("/search")
    public ResponseEntity<List<AgentDTO>> findAgentsByName(@RequestParam String name) {
        List<AgentDTO> agents = agentService.findAgentsByName(name);
        return ResponseEntity.ok(agents);
    }

    /**
     * Adds a property to an agent's managed properties.
     *
     * @param agentId     The ID of the agent.
     * @param propertyId  The ID of the property to add.
     * @return ResponseEntity with the updated agent DTO.
     */
    @PostMapping("/{agentId}/properties/{propertyId}")
    public ResponseEntity<AgentDTO> addManagedProperty(@PathVariable Long agentId, @PathVariable Long propertyId) {
        AgentDTO updatedAgent = agentService.addManagedProperty(agentId, propertyId);
        return ResponseEntity.ok(updatedAgent);
    }

    /**
     * Adds a client to an agent.
     *
     * @param agentId  The ID of the agent.
     * @param clientId The ID of the client to add.
     * @return ResponseEntity with the updated agent DTO.
     */
    @PostMapping("/{agentId}/clients/{clientId}")
    public ResponseEntity<AgentDTO> addClient(@PathVariable Long agentId, @PathVariable Long clientId) {
        AgentDTO updatedAgent = agentService.addClient(agentId, clientId);
        return ResponseEntity.ok(updatedAgent);
    }

    /**
     * Retrieves all clients managed by a specific agent.
     *
     * @param agentId The ID of the agent.
     * @return ResponseEntity with the list of client DTOs managed by the agent.
     */
    @GetMapping("/{agentId}/clients")
    public ResponseEntity<List<ClientDTO>> getClientsByAgent(@PathVariable Long agentId) {
        List<ClientDTO> clients = agentService.getClientsByAgent(agentId);
        return ResponseEntity.ok(clients);
    }

    /**
     * Retrieves all properties managed by a specific agent.
     *
     * @param agentId The ID of the agent.
     * @return ResponseEntity with the list of property DTOs managed by the agent.
     */
    @GetMapping("/{agentId}/properties")
    public ResponseEntity<List<PropertyDTO>> getManagedPropertiesByAgent(@PathVariable Long agentId) {
        List<PropertyDTO> properties = agentService.getManagedPropertiesByAgent(agentId);
        return ResponseEntity.ok(properties);
    }

}
