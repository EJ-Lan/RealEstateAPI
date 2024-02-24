package com.codewithej.realestateapi.service;

import com.codewithej.realestateapi.dto.ClientDTO;

import java.util.List;
import java.util.Set;

public interface ClientService {

    ClientDTO createClient(ClientDTO clientDTO);

    ClientDTO updateClient(Long id, ClientDTO clientDTO);

    void deleteClient(Long id);

    ClientDTO getClientById(Long id);

    List<ClientDTO> getAllClients();

    List<ClientDTO> findClientsByName(String name);

    ClientDTO setClientAgent(Long clientId, Long agentId);

    Set<ClientDTO> setInterestedProperties(Long clientId, Set<Long> propertyIds);

    Set<ClientDTO> getInterestedProperties(Long clientId);
}

