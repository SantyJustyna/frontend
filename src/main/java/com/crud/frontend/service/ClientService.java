package com.crud.frontend.service;

import com.crud.frontend.domain.Client;
import com.vaadin.flow.spring.annotation.SpringComponent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class ClientService {
    private final RestTemplate restTemplate;
    private static final String BACKEND_URL = "http://localhost:8080/v1/clients";

    @Autowired
    public ClientService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Client> getAllClients() {
        ResponseEntity<Client[]> response = restTemplate.getForEntity(BACKEND_URL, Client[].class);
        return Arrays.asList(response.getBody());
    }

    public Client getClientById(Long clientId) {
        String url = BACKEND_URL + "/" + clientId;
        ResponseEntity<Client> response = restTemplate.getForEntity(url, Client.class);
        return response.getBody();
    }

    public void createClient(Client client) {
        HttpEntity<Client> request = new HttpEntity<>(client);
        restTemplate.postForEntity(BACKEND_URL, request, Void.class);
    }

    public void updateClient(Client client) {
        HttpEntity<Client> request = new HttpEntity<>(client);
        restTemplate.exchange(BACKEND_URL, HttpMethod.PUT, request, Client.class);
    }

    public void deleteClient(Long clientId) {
        String url = BACKEND_URL + "/" + clientId;
        restTemplate.exchange(url, HttpMethod.DELETE, null, Void.class);
    }
}
