package com.example.demo.controller;

import com.example.demo.model.Guess;
import com.example.demo.service.GuessService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class GuessControllerTest {

    @Mock
    private GuessService guessService;

    @InjectMocks
    private GuessController guessController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAvailableGuess() throws Exception {

        List<Guess> expectedOrder = new ArrayList<>();
        Guess guess1 = new Guess();
        guess1.setId(1L);
        guess1.setName("John Doe");
        expectedOrder.add(guess1);

        String time = LocalDateTime.now().toString();
        when(guessService.findAvailableGuess(any(String.class))).thenReturn(expectedOrder);
        List<Guess> guesses = guessController.findAvailableGuess(time);

        Assertions.assertEquals(expectedOrder, guesses);
    }

    // todo write test of negative cases and other methods

}