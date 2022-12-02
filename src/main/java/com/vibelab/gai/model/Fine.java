package com.vibelab.gai.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class Fine {
    private int idFine;
    @NotEmpty(message = "number of car should not be empty")
    @Size(min = 17, max = 17, message = "number of car should be 17 characters")
    private String numberOfCar;
    @NotEmpty(message = "intruder should not be empty")
    @Size(min = 5, max = 50, message = "intruder should be between 5 and 50 characters")
    private String intruder;
    @NotEmpty(message = "traffic cop should not be empty")
    @Size(min = 5, max = 50, message = "traffic cop should be between 5 and 50 characters")
    private String trafficCop;
    private LocalDate dateFine;
    private LocalTime timeFine;
    @Min(value = 0, message = "sum should be greater than 0")
    private int sum;
    private boolean agenda;
    private boolean paid;
    private LocalDate dateDeadline;

    public Fine(int idFine, String numberOfCar, String intruder, String trafficCop, String dateFine,
                String timeFine, int sum, boolean agenda, boolean paid, String dateDeadline) {
        this.idFine = idFine;
        this.numberOfCar = numberOfCar;
        this.intruder = intruder;
        this.trafficCop = trafficCop;
        this.sum = sum;
        this.agenda = agenda;
        this.paid = paid;
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HH:mm");
        this.dateFine = LocalDate.parse(dateFine, formatterDate);
        this.timeFine = LocalTime.parse(timeFine, formatterTime);
        this.dateDeadline = LocalDate.parse(dateDeadline, formatterDate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fine fine = (Fine) o;
        return idFine == fine.idFine && sum == fine.sum && agenda == fine.agenda && paid == fine.paid
                && Objects.equals(numberOfCar, fine.numberOfCar)
                && Objects.equals(intruder, fine.intruder)
                && Objects.equals(trafficCop, fine.trafficCop)
                && Objects.equals(dateFine, fine.dateFine)
                && Objects.equals(timeFine, fine.timeFine)
                && Objects.equals(dateDeadline, fine.dateDeadline);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idFine, numberOfCar, intruder, trafficCop, dateFine, timeFine, sum, agenda, paid, dateDeadline);
    }
}
