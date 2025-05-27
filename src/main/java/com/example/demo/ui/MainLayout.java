package com.example.demo.ui;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLink;

public class MainLayout extends AppLayout {

    public MainLayout() {

        var header = new HorizontalLayout(
                new DrawerToggle(),
                new H3("Vaadin ❤️ Java")
        );
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        addToNavbar(header);

        addToDrawer(new VerticalLayout(
                new RouterLink("Contacts", ContactsView.class),
                new RouterLink("AI Chat", AiChatView.class)
        ));
    }
}
