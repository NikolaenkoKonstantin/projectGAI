package com.vibelab.gai.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseFineDTO {

    private int idFine;

    private String numberOfCar;

    private String intruder;

    private String trafficCop;

    private int sum;

    private boolean subpoena;

    private boolean paid;
}
