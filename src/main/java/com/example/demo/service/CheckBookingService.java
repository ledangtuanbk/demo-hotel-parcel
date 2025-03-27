package com.example.demo.service;


import com.example.demo.model.Booking;

public interface CheckBookingService {
    Booking findLastBooking(Long guessId);
}
