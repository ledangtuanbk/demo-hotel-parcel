package com.example.demo.service;


import com.example.demo.model.Parcel;
import com.example.demo.model.dto.requests.ParcelCreateRequest;
import jakarta.validation.Valid;

import java.util.List;

public interface ParcelService {

    Parcel create(@Valid ParcelCreateRequest createGuessRequest);

    Parcel pickUp(@Valid Long id);

    List<Parcel> findAvailableParcel(@Valid Long guessId);
}
