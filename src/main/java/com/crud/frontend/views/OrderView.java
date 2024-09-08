package com.crud.frontend.views;

import com.crud.frontend.domain.Order;
import com.crud.frontend.domain.OrderForm;
import com.crud.frontend.service.OrderService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("orders")
public class OrderView extends VerticalLayout {
    private final OrderService orderService; // Usługa do pobierania zamówień z backendu

    private Grid<Order> orderGrid = new Grid<>(Order.class);
    private Button addOrderButton = new Button("Dodaj Zamówienie");

    public OrderView(OrderService orderService) {
        this.orderService = orderService;

        // Ustawienie kolumn
        orderGrid.setColumns("id", "orderReference", "loadingPlace", "deliveryPlace", "loadingDate", "deliveryDate", "completed", "driverSurname", "plateNumber");

        // Dodanie kolumny z przyciskiem do zmiany statusu
        orderGrid.addComponentColumn(order -> {
            Button completeButton = new Button("Mark as Completed");
            completeButton.addClickListener(e -> markAsCompleted(order));
            return completeButton;
        });

        // Pobiera dane z backendu
        updateGridItems(); // Wywołanie metody do pobrania danych i ustawienia ich w gridzie

        // Przycisk do dodania nowego zamówienia
        addOrderButton.addClickListener(e -> openOrderForm(new Order()));

        add(orderGrid, addOrderButton);
    }

    private void openOrderForm(Order order) {
        Dialog dialog = new Dialog();
        OrderForm form = new OrderForm(order, orderService);
        dialog.add(form);
        dialog.open();
    }

    private void markAsCompleted(Order order) {
        if (order.getId() != null) {
            orderService.markOrderAsCompleted(order.getId()); // Oznacz zamówienie jako ukończone
            Notification.show("Order marked as completed");
            updateGridItems(); // Odśwież widok
        }
    }

    private void updateGridItems() {
        // Pobiera zaktualizowane dane z backendu i ustawia je w gridzie
        orderGrid.setItems(orderService.getAllOrders());
    }
}
