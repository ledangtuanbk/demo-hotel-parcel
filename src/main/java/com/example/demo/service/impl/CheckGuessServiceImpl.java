package com.example.demo.service.impl;

import com.example.demo.model.Booking;
import com.example.demo.model.dto.requests.BaseService;
import com.example.demo.service.CheckGuessService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CheckGuessServiceImpl extends BaseService implements CheckGuessService {

    private final CheckBookingServiceImpl checkBookingService;

    @Override
    public boolean isAvailable(Long guessId) {
        log.info("Checking if guess is available");
        Booking lastBooking = checkBookingService.findLastBooking(guessId);
        // check if user not check in
        return lastBooking != null && lastBooking.getCheckOutDate() == null;
    }
}
