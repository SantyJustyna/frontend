package com.crud.frontend.domain;

import lombok.Data;

import java.time.LocalDate;
@Data
public class Order {
    private Long id;
    private String orderReference;
    private String loadingPlace;
    private String deliveryPlace;
    private LocalDate loadingDate;
    private LocalDate deliveryDate;
    private Boolean completed;
    private String driverSurname;
    private String plateNumber;
}

