package com.example.demo.service.impl;

import com.example.demo.model.Booking;
import com.example.demo.model.Guess;
import com.example.demo.model.dto.requests.BaseService;
import com.example.demo.model.dto.requests.CreateGuessRequest;
import com.example.demo.repository.BookingRepository;
import com.example.demo.repository.GuessRepository;
import com.example.demo.service.GuessService;
import com.example.demo.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class GuessServiceImpl extends BaseService implements GuessService {

    private final GuessRepository repository;
    private final BookingRepository bookingRepository;
    private final ModelMapper modelMapper;

    @Override
    public Guess create(CreateGuessRequest createGuessRequest) {
        // check if the guess already exists
        Guess guess = repository.findByIdentity(createGuessRequest.getIdentity());
        if (guess != null) {
            // update the guess
            guess.setName(createGuessRequest.getName());
            return repository.save(guess);
        } else {
            guess = modelMapper.map(createGuessRequest, Guess.class);
            // save the guess
            return repository.save(guess);
        }

        // todo we can convert the guess to a dto and return it
    }

    // check whether the guess is not checked out to be able to receipt the parcel
    @Override
    public Guess checkAvailable(Long id) {
        Optional<Guess> guessOptional = repository.findById(id);
        // check if the guess does not exist throw exception
        if (guessOptional.isEmpty()) {
            throw new RuntimeException("Guess not found");
        }
        Guess guess = guessOptional.get();
        // check if the guess is already checked out
        List<Booking> bookings = bookingRepository.findByGuessIdOrderByCheckInDateDesc(id);
        if (bookings.isEmpty()) {
            // throw exception that the guess is not booking here
        }
        Booking booking = bookings.get(0);
        if (booking.getCheckOutDate() != null) {
            // throw exception that the guess is already checked out
            throw new RuntimeException("Guess already checked out");
        }
        // todo we can convert the guess to a dto and return it
        return guess;
    }

    @Override
    public List<Guess> findAvailableGuess(String time) {
        log.info("find available guess");
        log.info("time: {}", time);
        // convert the time to LocalDateTime
        LocalDateTime localDateTime = DateUtil.parseDate(time);

        // given time which guests are checked into the hotel and have not checked out,
        // return the list of guests who are checked in and not checked out
        List<Booking> bookings = bookingRepository.findByCheckInDateBeforeAndCheckOutDateIsNull(localDateTime);

        List<Guess> guesses = new ArrayList<>();
        for (Booking booking : bookings) {
            guesses.add(booking.getGuess());
        }

        return guesses;
    }
}
