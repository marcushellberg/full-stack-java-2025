package com.example.demo.ui;

import com.example.demo.data.Contact;
import com.example.demo.data.ContactRepository;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;

@Route(value = "", layout = MainLayout.class)
public class ContactsView extends VerticalLayout {

    private final ContactRepository contactRepository;

    Grid<Contact> grid = new Grid<>(Contact.class);

    public ContactsView(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
        var form = new ContactForm();
        form.setVisible(false);

        grid.setColumns("name", "email", "phone", "birthday");
        grid.asSingleSelect().addValueChangeListener(e -> {
            form.setVisible(e.getValue() != null);
            form.setContact(e.getValue());
        });

        updateContacts(contactRepository);

        add(grid, form);
    }

    private void updateContacts(ContactRepository contactRepository) {
        grid.setItems(contactRepository.findAll());
    }


    class ContactForm extends FormLayout {
        TextField name = new TextField("Name");
        TextField email = new TextField("Email");
        TextField phone = new TextField("Phone");
        DatePicker birthday = new DatePicker("Birthday");
        Button save = new Button("Save");

        Binder<Contact> binder = new BeanValidationBinder<>(Contact.class);

        ContactForm() {
            binder.bindInstanceFields(this);

            save.addClickListener(e -> {
                if (binder.isValid()) {
                    contactRepository.save(binder.getBean());
                    updateContacts(contactRepository);
                }
            });

            add(name, email, phone, birthday, save);
        }

        void setContact(Contact contact) {
            binder.setBean(contact);
        }
    }
}
