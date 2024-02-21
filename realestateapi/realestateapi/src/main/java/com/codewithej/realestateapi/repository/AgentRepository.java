package com.codewithej.realestateapi.repository;

import com.codewithej.realestateapi.model.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgentRepository extends JpaRepository<Agent, Long> {
    List<Agent> findByNameContainingIgnoreCase(String name);
}
