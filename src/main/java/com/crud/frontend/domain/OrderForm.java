package com.crud.frontend.domain;

import com.crud.frontend.service.OrderService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.checkbox.Checkbox;

public class OrderForm extends VerticalLayout {
    private final OrderService orderService;

    private TextField orderReference = new TextField("Order Reference");
    private TextField loadingPlace = new TextField("Loading Place");
    private TextField deliveryPlace = new TextField("Delivery Place");
    private DatePicker loadingDate = new DatePicker("Loading Date");
    private DatePicker deliveryDate = new DatePicker("Delivery Date");
    private Checkbox completed = new Checkbox("Completed");
    private TextField driverSurname = new TextField("Driver Surname");
    private TextField vehiclePlateNumber = new TextField("Vehicle Plate Number");

    private Button saveButton = new Button("Save");
    private Button completeOrderButton = new Button("Complete Order");

    private Order order;

    public OrderForm(Order order, OrderService orderService) {
        this.orderService = orderService;
        this.order = order;

        add(orderReference, loadingPlace, deliveryPlace, loadingDate, deliveryDate, driverSurname, vehiclePlateNumber, completed, saveButton, completeOrderButton);

        saveButton.addClickListener(e -> saveOrder());
        completeOrderButton.addClickListener(e -> completeOrder());

        if (order.getId() != null) {
            bindFormFields(order);
        }
    }

    private void bindFormFields(Order order) {
        orderReference.setValue(order.getOrderReference());
        loadingPlace.setValue(order.getLoadingPlace());
        deliveryPlace.setValue(order.getDeliveryPlace());
        loadingDate.setValue(order.getLoadingDate());
        deliveryDate.setValue(order.getDeliveryDate());
        completed.setValue(order.getCompleted());
        driverSurname.setValue(order.getDriverSurname());
        vehiclePlateNumber.setValue(order.getPlateNumber());
    }

    private void saveOrder() {
        Order orderDto = new Order();
        orderDto.setOrderReference(orderReference.getValue());
        orderDto.setLoadingPlace(loadingPlace.getValue());
        orderDto.setDeliveryPlace(deliveryPlace.getValue());
        orderDto.setLoadingDate(loadingDate.getValue());
        orderDto.setDeliveryDate(deliveryDate.getValue());
        orderDto.setCompleted(completed.getValue());
        orderDto.setDriverSurname(driverSurname.getValue());
        orderDto.setPlateNumber(vehiclePlateNumber.getValue());

        orderService.updateOrder(orderDto); // Zapisz zamówienie w backendzie
        Notification.show("Order saved");
    }

    private void completeOrder() {
        orderService.markOrderAsCompleted(order.getId()); // Oznacz zamówienie jako ukończone
        Notification.show("Order completed");
    }
}
