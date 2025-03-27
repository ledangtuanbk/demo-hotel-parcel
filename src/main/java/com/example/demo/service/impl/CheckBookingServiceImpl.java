package com.example.demo.service.impl;

import com.example.demo.model.Booking;
import com.example.demo.model.dto.requests.BaseService;
import com.example.demo.repository.BookingRepository;
import com.example.demo.service.CheckBookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CheckBookingServiceImpl extends BaseService implements CheckBookingService {

    private final BookingRepository repository;

    @Override
    public Booking findLastBooking(Long guessId) {
        List<Booking> bookings = repository.findByGuessIdOrderByCheckInDateDesc(guessId);

        // if the guess does not exist throw exception
        if (bookings.isEmpty()) {
            throw new RuntimeException("Booking not found");
        }
        return bookings.get(0);
    }
}
