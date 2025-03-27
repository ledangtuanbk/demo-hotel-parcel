package com.example.demo.controller;

import com.example.demo.model.Parcel;
import com.example.demo.model.dto.requests.ParcelCreateRequest;
import com.example.demo.service.ParcelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/parcels")
@RequiredArgsConstructor
public class ParcelController extends BaseController {

    private final ParcelService service;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    public Parcel create(@Valid @RequestBody ParcelCreateRequest parcelCreateRequest) {
        return service.create(parcelCreateRequest);
    }


    @PostMapping("/pick-up/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Parcel pickUp(@Valid @PathVariable Long id) {
        // can add request body logic to pick up a parcel when have time
        return service.pickUp(id);
    }

    // check whether the parcel is available to be picked up when the guess is checked out
    @PostMapping("/find-available-parcel/{guessId}")
    @ResponseStatus(HttpStatus.OK)
    public List<Parcel> findAvailableParcel(@Valid @PathVariable Long guessId) {
        return service.findAvailableParcel(guessId);
    }

}
