package com.codewithej.realestateapi.controller;

import com.codewithej.realestateapi.dto.ClientDTO;
import com.codewithej.realestateapi.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<ClientDTO> createClient(@RequestBody ClientDTO clientDTO) {
        ClientDTO createdClient = clientService.createClient(clientDTO);
        return new ResponseEntity<>(createdClient, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDTO> updateClient(@PathVariable Long id, @RequestBody ClientDTO clientDTO) {
        ClientDTO updatedClient = clientService.updateClient(id, clientDTO);
        return ResponseEntity.ok(updatedClient);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> getClientById(@PathVariable Long id) {
        ClientDTO client = clientService.getClientById(id);
        return ResponseEntity.ok(client);
    }

    @GetMapping
    public ResponseEntity<List<ClientDTO>> getAllClients() {
        List<ClientDTO> clients = clientService.getAllClients();
        return ResponseEntity.ok(clients);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ClientDTO>> findClientsByName(@RequestParam String name) {
        List<ClientDTO> clients = clientService.findClientsByName(name);
        return ResponseEntity.ok(clients);
    }

    @PostMapping("/{clientId}/setAgent/{agentId}")
    public ResponseEntity<ClientDTO> setClientAgent(@PathVariable Long clientId, @PathVariable Long agentId) {
        ClientDTO updatedClient = clientService.setClientAgent(clientId, agentId);
        return ResponseEntity.ok(updatedClient);
    }

    @PostMapping("/{clientId}/interested-properties")
    public ResponseEntity<Set<ClientDTO>> setInterestedProperties(@PathVariable Long clientId, @RequestBody Set<Long> propertyIds) {
        Set<ClientDTO> updatedClients = clientService.setInterestedProperties(clientId, propertyIds);
        return new ResponseEntity<>(updatedClients, HttpStatus.OK);
    }

    @GetMapping("/{clientId}/interested-properties")
    public ResponseEntity<Set<ClientDTO>> getInterestedProperties(@PathVariable Long clientId) {
        Set<ClientDTO> interestedProperties = clientService.getInterestedProperties(clientId);
        return ResponseEntity.ok(interestedProperties);
    }
}
