package com.example.demo.model.dto.requests;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CheckInRequest {

    @NotNull
    private Long guessId;

    @NotNull
    private LocalDateTime checkInDate;

}
