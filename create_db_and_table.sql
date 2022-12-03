CREATE DATABASE gai;

CREATE TABLE fine(
    id_fine INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    number_of_car varchar(17) not null unique,
    intruder varchar(50) not null,
    traffic_cop varchar(50) not null,
    date_fine date not null,
    time_fine time not null,
    sum int check(sum >= 0),
    agenda bool default false,
    paid bool default false,
    date_deadline date not null
);
