package com.crud.frontend.domain;

import lombok.Data;

@Data
public class Client {
    private Long id;
    private String name;
    private String address;
    private String mail;
    private String vatNumber;
}
