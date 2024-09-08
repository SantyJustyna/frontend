package com.crud.frontend.domain;

import com.crud.frontend.service.VehicleService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;


public class VehicleForm extends VerticalLayout {
    private final VehicleService vehicleService;

    private TextField plateNumber = new TextField("Plate Number");
    private TextField type = new TextField("Type");
    private DatePicker nextInspectionDate = new DatePicker("Inspection");

    private Button saveButton = new Button("Save");

    private Vehicle vehicle;

    public VehicleForm(Vehicle vehicle, VehicleService vehicleService) {
        this.vehicleService = vehicleService;
        this.vehicle = vehicle;

        add(plateNumber, type, nextInspectionDate, saveButton);

        saveButton.addClickListener(e -> saveVehicle());

        if (vehicle.getId() != null) {
            bindFormFields(vehicle);
        }
    }

    private void bindFormFields(Vehicle vehicle) {
        plateNumber.setValue(vehicle.getPlateNumber());
        type.setValue(vehicle.getType());
        nextInspectionDate.setValue(vehicle.getNextInspectionDate());
    }

    private void saveVehicle() {
        Vehicle vehicleDto = new Vehicle();
        vehicleDto.setPlateNumber(plateNumber.getValue());
        vehicleDto.setType(type.getValue());
        vehicleDto.setNextInspectionDate(nextInspectionDate.getValue());

        if (vehicle.getId() != null) {
            vehicleDto.setId(vehicle.getId());
            vehicleService.updateVehicle(vehicleDto);
        } else {
            vehicleService.createVehicle(vehicleDto);
        }

        Notification.show("Vehicle saved");
        UI.getCurrent().getPage().reload(); // Odświeża widok
    }
}
