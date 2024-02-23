package com.codewithej.realestateapi.service;


import com.codewithej.realestateapi.dto.AgentDTO;
import com.codewithej.realestateapi.dto.ClientDTO;
import com.codewithej.realestateapi.dto.PropertyDTO;

import java.util.List;

public interface AgentService {
    AgentDTO createAgent(AgentDTO agentDTO);

    AgentDTO updateAgent(Long id, AgentDTO agentDTO);

    void deleteAgent(Long id);

    AgentDTO getAgentById(Long id);

    List<AgentDTO> getAllAgents();

    List<AgentDTO> findAgentsByName(String name);

    AgentDTO addManagedProperty(Long agentId, Long propertyId);

    AgentDTO addClient(Long agentId, Long clientId);

    List<ClientDTO> getClientsByAgent(Long agentId);

    List<PropertyDTO> getManagedPropertiesByAgent(Long agentId);
}

