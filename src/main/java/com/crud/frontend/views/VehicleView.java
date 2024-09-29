package com.crud.frontend.views;

import com.crud.frontend.domain.Vehicle;
import com.crud.frontend.domain.VehicleForm;
import com.crud.frontend.service.VehicleService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("vehicles")
public class VehicleView extends VerticalLayout {
    private final VehicleService vehicleService;

    private Grid<Vehicle> vehicleGrid = new Grid<>(Vehicle.class);
    private Button addVehicleButton = new Button("Add Vehicle");

    public VehicleView(VehicleService vehicleService) {
        this.vehicleService = vehicleService;

        vehicleGrid.setColumns("id", "plateNumber", "type", "nextInspectionDate");
        vehicleGrid.setItems(vehicleService.getAllVehicles());

        addVehicleButton.addClickListener(e -> {
            openVehicleForm(new Vehicle());
        });

        add(vehicleGrid, addVehicleButton);
    }

    private void openVehicleForm(Vehicle vehicle) {
        Dialog dialog = new Dialog();
        VehicleForm form = new VehicleForm(vehicle, vehicleService);
        dialog.add(form);
        dialog.open();
    }
}
