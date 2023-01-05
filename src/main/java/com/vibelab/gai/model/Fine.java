package com.vibelab.gai.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "fine")
public class Fine {
    @Id
    @Column(name = "id_fine")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idFine;

    @Column(name = "number_of_car")
    private String numberOfCar;

    @Column(name = "intruder")
    private String intruder;

    @Column(name = "traffic_cop")
    private String trafficCop;

    @Column(name = "date_time_fine")
    private LocalDateTime dateTimeFine;

    @Column(name = "date_payment")
    private LocalDate datePayment;

    @Column(name = "sum")
    private int sum;

    @Column(name = "subpoena")
    private boolean subpoena;

    @Column(name = "paid")
    private boolean paid;

    @Column(name = "date_deadline")
    private LocalDate dateDeadline;

    @Column(name = "overdue")
    private boolean overdue;

}
