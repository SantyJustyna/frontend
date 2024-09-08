package com.crud.frontend.views;

import com.crud.frontend.domain.Driver;
import com.crud.frontend.domain.DriverForm;
import com.crud.frontend.service.DriverService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("drivers")
public class DriverView extends VerticalLayout {
    private final DriverService driverService; // Usługa do pobierania kierowców z backendu

    private Grid<Driver> driverGrid = new Grid<>(Driver.class);
    private Button addDriverButton = new Button("Dodaj Kierowcę");

    public DriverView(DriverService driverService) {
        this.driverService = driverService;

        driverGrid.setColumns("id", "name", "surname");
        driverGrid.setItems(driverService.getAllDrivers()); // Pobiera dane z backendu

        addDriverButton.addClickListener(e -> {
            openDriverForm(new Driver());
        });

        add(driverGrid, addDriverButton);
    }

    private void openDriverForm(Driver driver) {
        Dialog dialog = new Dialog();
        DriverForm form = new DriverForm(driver, driverService);
        dialog.add(form);
        dialog.open();
    }
}
