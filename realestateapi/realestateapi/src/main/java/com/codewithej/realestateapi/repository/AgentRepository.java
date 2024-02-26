package com.codewithej.realestateapi.repository;

import com.codewithej.realestateapi.model.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for {@link Agent} entities.
 * <p>
 * Extends JpaRepository to provide CRUD operations and custom queries for Agent entities.
 * </p>
 */
@Repository
public interface AgentRepository extends JpaRepository<Agent, Long> {

    /**
     * Finds agents whose names contain the given string, ignoring case.
     *
     * @param name The string to search for within agent names.
     * @return A list of agents whose names contain the specified string, case-insensitively.
     */
    List<Agent> findByNameContainingIgnoreCase(String name);
}
