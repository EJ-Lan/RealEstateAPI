package com.codewithej.realestateapi.repository;

import com.codewithej.realestateapi.model.Agent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class AgentRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AgentRepository agentRepository;

    private Agent agent;

    @BeforeEach
    public void setUp() {
        agent = new Agent();
        agent.setName("John Doe");
        agent.setEmail("john.doe@example.com");
        agent.setPhoneNumber("1234567890");
        entityManager.persist(agent);
        entityManager.flush();
    }

    @Test
    public void whenFindById_thenReturnAgent() {
        Agent foundAgent = agentRepository.findById(agent.getId()).orElse(null);

        assertThat(foundAgent).isNotNull();
        assertThat(foundAgent.getName()).isEqualTo(agent.getName());
    }

    @Test
    public void whenFindByNameContainingIgnoreCase_thenReturnAgents() {
        List<Agent> foundAgents = agentRepository.findByNameContainingIgnoreCase("john");

        assertThat(foundAgents).hasSize(1);
        assertThat(foundAgents.get(0).getName()).isEqualToIgnoringCase("john doe");
    }

}
