package com.codewithej.realestateapi.repository;

import com.codewithej.realestateapi.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    List<Client> findByNameContainingIgnoreCase(String name);

    List<Client> findByAgentId(Long agentId);
}
