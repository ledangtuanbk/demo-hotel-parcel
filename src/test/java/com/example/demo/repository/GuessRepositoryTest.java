package com.example.demo.repository;

import com.example.demo.model.Guess;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class GuessRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private GuessRepository orderRepository;

    @BeforeEach
    void setUp() {
        // Set up initial data for testing
        Guess guess = getGuess();
        entityManager.persist(guess);
    }

    private static Guess getGuess() {
        Guess guess = new Guess();
//        guess.setId(1L);
        guess.setName("John Doe");
        guess.setIdentity("123456789");
        return guess;
    }

    @Test
    void test_findByIdentity_success() {
        Guess guess = orderRepository.findByIdentity("123456789");
        Guess expected = getGuess();
        assertEquals(expected.getName(), guess.getName());
        assertEquals(expected.getIdentity(), guess.getIdentity());
    }

    // todo add more test cases for failure cases and other methods
}