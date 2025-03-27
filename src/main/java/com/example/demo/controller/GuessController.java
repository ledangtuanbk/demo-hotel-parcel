package com.example.demo.controller;

import com.example.demo.model.Guess;
import com.example.demo.model.dto.requests.CreateGuessRequest;
import com.example.demo.service.GuessService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/guesses")
@RequiredArgsConstructor
public class GuessController extends BaseController {

    private final GuessService service;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    public Guess create(@Valid @RequestBody CreateGuessRequest createGuessRequest) {
        return service.create(createGuessRequest);
    }

    // check whether the guess is not checked out to be able to receipt the parcel
    @GetMapping("/check-available/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Guess checkAvailable(@Valid @PathVariable Long id) {
        return service.checkAvailable(id);
    }

    // given time which guests are checked into the hotel and have not checked out,
    // return the list of guests who are checked in and not checked out
    @GetMapping("/find-available-guess/{time}")
    @ResponseStatus(HttpStatus.OK)
    public List<Guess> findAvailableGuess(@PathVariable String time) {
        return service.findAvailableGuess(time);
    }

}
