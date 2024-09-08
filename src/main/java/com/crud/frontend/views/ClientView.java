package com.crud.frontend.views;

import com.crud.frontend.domain.Client;
import com.crud.frontend.domain.ClientForm;
import com.crud.frontend.service.ClientService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("clients")
public class ClientView extends VerticalLayout {
    private final ClientService clientService; // Usługa do pobierania klientów z backendu

    private Grid<Client> clientGrid = new Grid<>(Client.class);
    private Button addClientButton = new Button("Dodaj Klienta");

    public ClientView(ClientService clientService) {
        this.clientService = clientService;

        clientGrid.setColumns("id", "name", "address", "mail", "vatNumber");
        clientGrid.setItems(clientService.getAllClients()); // Pobiera dane z backendu

        addClientButton.addClickListener(e -> {
            openClientForm(new Client());
        });

        add(clientGrid, addClientButton);
    }

    private void openClientForm(Client client) {
        Dialog dialog = new Dialog();
        ClientForm form = new ClientForm(client, clientService);
        dialog.add(form);
        dialog.open();
    }
}
