package com.example.demo.controller;

import com.example.demo.model.Booking;
import com.example.demo.model.dto.requests.CheckInRequest;
import com.example.demo.model.dto.requests.CheckOutRequest;
import com.example.demo.service.BookingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/bookings")
@RequiredArgsConstructor
public class BookingController extends BaseController {

    private final BookingService service;

    @PostMapping("/check-in")
    @ResponseStatus(HttpStatus.OK)
    public Booking checkIn(@Valid @RequestBody CheckInRequest checkInRequest) {
        return service.checkIn(checkInRequest);
    }

    @PostMapping("/check-out")
    @ResponseStatus(HttpStatus.OK)
    public Booking checkOut(@Valid @RequestBody CheckOutRequest checkOutRequest) {
        return service.checkOut(checkOutRequest);
    }

}
