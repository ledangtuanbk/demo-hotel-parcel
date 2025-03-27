package com.example.demo.service;


public interface CheckGuessService {

    // check if the guess is available in the hotel (Check in and not check out)
    boolean isAvailable(Long guessId);

}
