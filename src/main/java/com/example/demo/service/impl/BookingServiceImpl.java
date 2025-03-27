package com.example.demo.service.impl;

import com.example.demo.model.Booking;
import com.example.demo.model.Guess;
import com.example.demo.model.dto.requests.BaseService;
import com.example.demo.model.dto.requests.CheckInRequest;
import com.example.demo.model.dto.requests.CheckOutRequest;
import com.example.demo.repository.BookingRepository;
import com.example.demo.repository.GuessRepository;
import com.example.demo.service.BookingService;
import com.example.demo.service.CheckBookingService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl extends BaseService implements BookingService {

    private final BookingRepository repository;
    private final GuessRepository guessRepository;
    private final ModelMapper modelMapper;
    private final CheckBookingService checkBookingService;

    @Override
    public Booking checkIn(CheckInRequest checkInRequest) {
        // check if the guess already exists
        Optional<Guess> guessOptional = guessRepository.findById(checkInRequest.getGuessId());
        // if the guess does not exist throw exception
        if (guessOptional.isEmpty()) {
            throw new RuntimeException("Guess not found");
        }
        Guess guess = guessOptional.get();
        // create booking here
        Booking booking = new Booking();
        booking.setGuess(guess);
        booking.setCheckInDate(checkInRequest.getCheckInDate());

        // save the booking and return
        // todo we can convert the booking to a dto and return it
        return repository.save(booking);
    }

    @Override
    public Booking checkOut(CheckOutRequest checkOutRequest) {
        Booking booking = checkBookingService.findLastBooking(checkOutRequest.getGuessId());
        // check if the booking is already checked out
        if (booking.getCheckOutDate() != null) {
            throw new RuntimeException("Booking already checked out");
        }

        booking.setCheckOutDate(checkOutRequest.getCheckOutDate());

        // save the booking
        // todo we can convert the booking to a dto and return it
        return repository.save(booking);
    }
}
