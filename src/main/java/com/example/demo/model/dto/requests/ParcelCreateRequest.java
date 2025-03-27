package com.example.demo.model.dto.requests;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ParcelCreateRequest {

    @NotNull
    private Long guessId;

    @NotNull
    @NotEmpty
    private String name;

    // other fields can be added here as needed

}
