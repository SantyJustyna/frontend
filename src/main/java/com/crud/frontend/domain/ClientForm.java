package com.crud.frontend.domain;

import com.crud.frontend.service.ClientService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;

public class ClientForm extends VerticalLayout {
    private final ClientService clientService;
    private TextField name = new TextField("Name");
    private TextField address = new TextField("Address");
    private TextField mail = new TextField("Mail");
    private TextField vatNumber = new TextField("VAT Number");
    private Button saveButton = new Button("Save");
    private Client client;

    public ClientForm(Client client, ClientService clientService) {
        this.clientService = clientService;
        this.client = client;

        add(name, address, mail, vatNumber, saveButton);

        saveButton.addClickListener(e -> saveClient());

        if (client.getId() != null) {
            bindFormFields(client);
        }
    }

    private void bindFormFields(Client client) {
        name.setValue(client.getName());
        address.setValue(client.getAddress());
        mail.setValue(client.getMail());
        vatNumber.setValue(client.getVatNumber());
    }

    private void saveClient() {
        Client clientDto = new Client();
        clientDto.setName(name.getValue());
        clientDto.setAddress(address.getValue());
        clientDto.setMail(mail.getValue());
        clientDto.setVatNumber(vatNumber.getValue());

        if (client.getId() != null) {
            clientDto.setId(client.getId());
            clientService.updateClient(clientDto);
        } else {
            clientService.createClient(clientDto);
        }

        Notification.show("Client saved");
        UI.getCurrent().getPage().reload();
    }
}
