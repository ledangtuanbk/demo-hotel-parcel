package com.example.demo.service.impl;

import com.example.demo.model.Guess;
import com.example.demo.model.Parcel;
import com.example.demo.model.dto.requests.BaseService;
import com.example.demo.model.dto.requests.ParcelCreateRequest;
import com.example.demo.repository.GuessRepository;
import com.example.demo.repository.ParcelRepository;
import com.example.demo.service.CheckGuessService;
import com.example.demo.service.ParcelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ParcelServiceImpl extends BaseService implements ParcelService {

    private final ParcelRepository repository;
    private final GuessRepository guessRepository;
    private final ModelMapper modelMapper;
    private final CheckGuessService checkGuessService;


    @Override
    public Parcel create(@Valid ParcelCreateRequest createGuessRequest) {

        Guess guess = getGuess(createGuessRequest.getGuessId());

        // check if the guess is already checked out
        boolean available = checkGuessService.isAvailable(createGuessRequest.getGuessId());
        if (!available) {
            throw new RuntimeException("Guess is not checkin or already checked out");
        }

        Parcel parcel = new Parcel();
        parcel.setGuess(guess);
        parcel.setName(createGuessRequest.getName());
        parcel.setAvailable(true);

        // save the parcel
        // todo we can convert the parcel to a dto and return it
        return repository.save(parcel);
    }

    private Guess getGuess(Long guessId) {
        Optional<Guess> guessOptional = guessRepository.findById(guessId);
        // if the guess does not exist throw exception
        if (guessOptional.isEmpty()) {
            throw new RuntimeException("Guess not found");
        }

        Guess guess = guessOptional.get();
        return guess;
    }

    // when the guess pick up the parcel
    @Override
    public Parcel pickUp(@Valid Long id) {
        Parcel parcel = repository.findById(id).orElse(null);
        // if the parcel does not exist throw exception
        if (parcel == null) {
            throw new RuntimeException("Parcel not found");
        }
        // check if the parcel is already picked up
        if (!parcel.isAvailable()) {
            throw new RuntimeException("Parcel already picked up");
        }
        // set the parcel as picked up
        parcel.setAvailable(false);
        // todo we can convert the parcel to a dto and return it
        return repository.save(parcel);
    }

    // get list parcel by guess id
    @Override
    public List<Parcel> findAvailableParcel(Long guessId) {
        Guess guess = getGuess(guessId);
        // todo we can convert the parcel to a dto and return it
        return repository.findByGuessIdAndAvailableTrue(guess.getId());
    }
}
