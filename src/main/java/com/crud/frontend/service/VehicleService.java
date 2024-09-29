package com.crud.frontend.service;

import com.crud.frontend.domain.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class VehicleService {
    private final RestTemplate restTemplate;
    private static final String BACKEND_URL = "http://localhost:8080/v1/vehicles";

    @Autowired
    public VehicleService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Vehicle> getAllVehicles() {
        ResponseEntity<Vehicle[]> response = restTemplate.getForEntity(BACKEND_URL, Vehicle[].class);
        return Arrays.asList(response.getBody());
    }

    public Vehicle getVehicleById(Long vehicleId) {
        String url = BACKEND_URL + "/" + vehicleId;
        ResponseEntity<Vehicle> response = restTemplate.getForEntity(url, Vehicle.class);
        return response.getBody();
    }

    public void createVehicle(Vehicle vehicle) {
        HttpEntity<Vehicle> request = new HttpEntity<>(vehicle);
        restTemplate.postForEntity(BACKEND_URL, request, Void.class);
    }

    public void updateVehicle(Vehicle vehicle) {
        HttpEntity<Vehicle> request = new HttpEntity<>(vehicle);
        restTemplate.exchange(BACKEND_URL, HttpMethod.PUT, request, Vehicle.class);
    }

    public void deleteVehicle(Long vehicleId) {
        String url = BACKEND_URL + "/" + vehicleId;
        restTemplate.exchange(url, HttpMethod.DELETE, null, Void.class);
    }
}
