package com.example.demo.service;

import com.example.demo.model.Booking;
import com.example.demo.model.Guess;
import com.example.demo.repository.BookingRepository;
import com.example.demo.service.impl.GuessServiceImpl;
import com.example.demo.util.DateUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class GuessServiceImplTest {

    @Mock
    private BookingRepository bookingRepository;

    @InjectMocks
    private GuessServiceImpl guessService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAvailableGuessSuccess() {
        String time = LocalDateTime.now().toString();
        LocalDateTime localDateTime = DateUtil.parseDate(time);

        List<Booking> bookings = new ArrayList<>();
        Guess guess = new Guess();
        guess.setId(1L);
        guess.setName("John Doe");
        guess.setIdentity("123456789");
        Booking booking = new Booking();
        booking.setGuess(guess);
        bookings.add(booking);

        when(bookingRepository.findByCheckInDateBeforeAndCheckOutDateIsNull(any(LocalDateTime.class))).thenReturn(bookings);

        List<Guess> actualGuesses = guessService.findAvailableGuess(time);
        Assertions.assertEquals(Arrays.asList(guess), actualGuesses);
    }

}