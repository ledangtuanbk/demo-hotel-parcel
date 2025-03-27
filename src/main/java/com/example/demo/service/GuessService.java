package com.example.demo.service;


import com.example.demo.model.Guess;
import com.example.demo.model.dto.requests.CreateGuessRequest;
import jakarta.validation.Valid;

import java.util.List;

public interface GuessService {

    Guess create(@Valid CreateGuessRequest createGuessRequest);

    Guess checkAvailable(@Valid Long id);

    List<Guess> findAvailableGuess(String time);
}
