package com.crud.frontend.domain;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Vehicle {
    private Long id;
    private String plateNumber;
    private String type;
    private LocalDate nextInspectionDate;
}
