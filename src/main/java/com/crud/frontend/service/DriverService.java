package com.crud.frontend.service;

import com.crud.frontend.domain.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class DriverService {
    private final RestTemplate restTemplate;
    private static final String BACKEND_URL = "http://localhost:8080/v1/drivers";

    @Autowired
    public DriverService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // Pobiera wszystkich kierowców z backendu
    public List<Driver> getAllDrivers() {
        ResponseEntity<Driver[]> response = restTemplate.getForEntity(BACKEND_URL, Driver[].class);
        return Arrays.asList(response.getBody());
    }

    // Pobiera kierowcę po jego ID
    public Driver getDriverById(Long driverId) {
        String url = BACKEND_URL + "/" + driverId;
        ResponseEntity<Driver> response = restTemplate.getForEntity(url, Driver.class);
        return response.getBody();
    }

    // Dodaje nowego kierowcę
    public void createDriver(Driver driver) {
        HttpEntity<Driver> request = new HttpEntity<>(driver);
        restTemplate.postForEntity(BACKEND_URL, request, Void.class);
    }

    // Aktualizuje istniejącego kierowcę
    public void updateDriver(Driver driver) {
        HttpEntity<Driver> request = new HttpEntity<>(driver);
        restTemplate.exchange(BACKEND_URL, HttpMethod.PUT, request, Driver.class);
    }

    // Usuwa kierowcę po ID
    public void deleteDriver(Long driverId) {
        String url = BACKEND_URL + "/" + driverId;
        restTemplate.exchange(url, HttpMethod.DELETE, null, Void.class);
    }
}
