package com.crud.frontend.domain;

import com.crud.frontend.service.DriverService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;

public class DriverForm extends VerticalLayout {
    private final DriverService driverService;
    private TextField name = new TextField("Name");
    private TextField surname = new TextField("Surname");
    private Button saveButton = new Button("Save");
    private Driver driver;

    public DriverForm(Driver driver, DriverService driverService) {
        this.driverService = driverService;
        this.driver = driver;

        add(name, surname, saveButton);

        saveButton.addClickListener(e -> saveDriver());

        if (driver.getId() != null) {
            bindFormFields(driver);
        }
    }

    private void bindFormFields(Driver driver) {
        name.setValue(driver.getName());
        surname.setValue(driver.getSurname());
    }

    private void saveDriver() {
        Driver driverDto = new Driver();
        driverDto.setName(name.getValue());
        driverDto.setSurname(surname.getValue());

        if (driver.getId() != null) {
            driverDto.setId(driver.getId());
            driverService.updateDriver(driverDto);
        } else {
            driverService.createDriver(driverDto);
        }

        Notification.show("Driver saved");
        UI.getCurrent().getPage().reload();
    }
}
