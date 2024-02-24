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

@RestController
@RequestMapping("/api/agents")
public class AgentController {

    private final AgentService agentService;

    @Autowired
    public AgentController(AgentService agentService) {
        this.agentService = agentService;
    }

    @PostMapping
    public ResponseEntity<AgentDTO> createAgent(@RequestBody AgentDTO agentDTO) {
        AgentDTO createdAgent = agentService.createAgent(agentDTO);
        return new ResponseEntity<>(createdAgent, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AgentDTO> updateAgent(@PathVariable Long id, @RequestBody AgentDTO agentDTO) {
        AgentDTO updatedAgent = agentService.updateAgent(id, agentDTO);
        return ResponseEntity.ok(updatedAgent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAgent(@PathVariable Long id) {
        agentService.deleteAgent(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgentDTO> getAgentById(@PathVariable Long id) {
        AgentDTO agent = agentService.getAgentById(id);
        return ResponseEntity.ok(agent);
    }

    @GetMapping
    public ResponseEntity<List<AgentDTO>> getAllAgents() {
        List<AgentDTO> agents = agentService.getAllAgents();
        return ResponseEntity.ok(agents);
    }

    @GetMapping("/search")
    public ResponseEntity<List<AgentDTO>> findAgentsByName(@RequestParam String name) {
        List<AgentDTO> agents = agentService.findAgentsByName(name);
        return ResponseEntity.ok(agents);
    }

    @PostMapping("/{agentId}/properties/{propertyId}")
    public ResponseEntity<AgentDTO> addManagedProperty(@PathVariable Long agentId, @PathVariable Long propertyId) {
        AgentDTO updatedAgent = agentService.addManagedProperty(agentId, propertyId);
        return ResponseEntity.ok(updatedAgent);
    }

    @PostMapping("/{agentId}/clients/{clientId}")
    public ResponseEntity<AgentDTO> addClient(@PathVariable Long agentId, @PathVariable Long clientId) {
        AgentDTO updatedAgent = agentService.addClient(agentId, clientId);
        return ResponseEntity.ok(updatedAgent);
    }

    @GetMapping("/{agentId}/clients")
    public ResponseEntity<List<ClientDTO>> getClientsByAgent(@PathVariable Long agentId) {
        List<ClientDTO> clients = agentService.getClientsByAgent(agentId);
        return ResponseEntity.ok(clients);
    }

    @GetMapping("/{agentId}/properties")
    public ResponseEntity<List<PropertyDTO>> getManagedPropertiesByAgent(@PathVariable Long agentId) {
        List<PropertyDTO> properties = agentService.getManagedPropertiesByAgent(agentId);
        return ResponseEntity.ok(properties);
    }

}
