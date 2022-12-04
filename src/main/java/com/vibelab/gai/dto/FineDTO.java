package com.vibelab.gai.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FineDTO {

    @NotEmpty(message = "number of car should not be empty")
    @Size(min = 17, max = 17, message = "number of car should be 17 characters")
    private String numberOfCar;

    @NotEmpty(message = "intruder should not be empty")
    @Size(min = 5, max = 50, message = "intruder should be between 5 and 50 characters")
    private String intruder;

    @NotEmpty(message = "traffic cop should not be empty")
    @Size(min = 5, max = 50, message = "traffic cop should be between 5 and 50 characters")
    private String trafficCop;

    @Min(value = 0, message = "sum should be greater than 0")
    private int sum;

    private boolean subpoena;

    private boolean paid;
}
