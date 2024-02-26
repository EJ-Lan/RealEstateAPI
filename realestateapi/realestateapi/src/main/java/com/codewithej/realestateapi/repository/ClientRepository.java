package com.codewithej.realestateapi.repository;

import com.codewithej.realestateapi.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for {@link Client} entities.
 * <p>
 * Extends JpaRepository to provide CRUD operations and custom queries for Client entities.
 * </p>
 */
@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    /**
     * Finds clients whose names contain the given string, ignoring case.
     *
     * @param name The string to search for within client names.
     * @return A list of clients whose names contain the specified string, case-insensitively.
     */
    List<Client> findByNameContainingIgnoreCase(String name);

    /**
     * Finds clients associated with a specific agent by the agent's ID.
     *
     * @param agentId The ID of the agent.
     * @return A list of clients associated with the specified agent.
     */
    List<Client> findByAgentId(Long agentId);
}
