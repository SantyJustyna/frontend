package com.crud.frontend.views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route
public class MainView extends VerticalLayout {
    public MainView() {
        Button ordersButton = new Button("Manage Orders", e -> UI.getCurrent().navigate(OrderView.class));
        Button driversButton = new Button("Manage Drivers", e -> UI.getCurrent().navigate(DriverView.class));
        Button vehiclesButton = new Button("Manage Vehicles", e -> UI.getCurrent().navigate(VehicleView.class));
        Button clientsButton = new Button("Manage Clients", e -> UI.getCurrent().navigate(ClientView.class));

        add(ordersButton, driversButton, vehiclesButton, clientsButton);
    }
}

