package com.codewithej.realestateapi.repository;

import com.codewithej.realestateapi.model.Agent;
import com.codewithej.realestateapi.model.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
public class ClientRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ClientRepository clientRepository;

    private Client client;
    private Agent agent;

    @BeforeEach
    public void setUp() {
        agent = new Agent();
        agent.setName("Agent Name");
        agent.setEmail("agent@example.com");
        agent.setPhoneNumber("1234567890");
        entityManager.persist(agent);

        client = new Client();
        client.setName("John Doe");
        client.setEmail("johndoe@example.com");
        client.setPhoneNumber("1234567890");
        client.setPreferences("Near park");
        client.setAgent(agent);
        entityManager.persist(client);
    }

    @Test
    public void whenFindByNameContainingIgnoreCaseThenClientShouldBeFound() {
        List<Client> foundClients = clientRepository.findByNameContainingIgnoreCase("john");
        assertThat(foundClients).hasSize(1);
        assertThat(foundClients.get(0).getName()).isEqualToIgnoringCase("John Doe");
    }

    @Test
    public void whenFindByAgentIdThenClientShouldBeFound() {
        List<Client> foundClients = clientRepository.findByAgentId(agent.getId());
        assertThat(foundClients).hasSize(1);
        assertThat(foundClients.get(0).getAgent().getId()).isEqualTo(agent.getId());
    }
}

