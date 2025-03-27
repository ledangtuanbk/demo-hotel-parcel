package com.example.demo.service;


import com.example.demo.model.Booking;
import com.example.demo.model.dto.requests.CheckInRequest;
import com.example.demo.model.dto.requests.CheckOutRequest;
import jakarta.validation.Valid;

public interface BookingService {

    Booking checkIn(@Valid CheckInRequest checkInRequest);

    Booking checkOut(@Valid CheckOutRequest checkOutRequest);
}
