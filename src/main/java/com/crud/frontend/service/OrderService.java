package com.crud.frontend.service;

import com.crud.frontend.domain.Order;
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
public class OrderService {
    private final RestTemplate restTemplate;
    private static final String BACKEND_URL = "http://localhost:8080/v1/orders";

    @Autowired
    public OrderService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Order> getAllOrders() {
        ResponseEntity<Order[]> response = restTemplate.getForEntity(BACKEND_URL, Order[].class);
        return Arrays.asList(response.getBody());
    }

    public Order getOrderById(Long orderId) {
        String url = BACKEND_URL + "/" + orderId;
        ResponseEntity<Order> response = restTemplate.getForEntity(url, Order.class);
        return response.getBody();
    }

    public void createOrder(Order order) {
        HttpEntity<Order> request = new HttpEntity<>(order);
        restTemplate.postForEntity(BACKEND_URL, request, Void.class);
    }

    public void updateOrder(Order orderDto) {
        HttpEntity<Order> request = new HttpEntity<>(orderDto);
        restTemplate.exchange(BACKEND_URL, HttpMethod.PUT, request, Order.class);
    }

    public void deleteOrder(Long orderId) {
        String url = BACKEND_URL + "/" + orderId;
        restTemplate.exchange(url, HttpMethod.DELETE, null, Void.class);
    }

    public void markOrderAsCompleted(Long orderId) {
        String url = BACKEND_URL + "/" + orderId + "/complete";
        restTemplate.put(url, null);
    }
}
