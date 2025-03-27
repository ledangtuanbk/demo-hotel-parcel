package com.example.demo.model.dto.requests;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CheckOutRequest {

    @NotNull
    private Long guessId;

    @NotNull
    private LocalDateTime checkOutDate;

}
