package com.example.demo.ui;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.markdown.Markdown;
import com.vaadin.flow.component.messages.MessageInput;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.dom.Style;
import com.vaadin.flow.router.Route;
import org.springframework.ai.chat.client.ChatClient;


@Route(value = "ai-chat", layout = MainLayout.class)
public class AiChatView extends VerticalLayout {

    public AiChatView(ChatClient.Builder builder) {
        var chatClient = builder.build();
        var ui = UI.getCurrent();

        var messageInput = new MessageInput();
        messageInput.getStyle().setPadding("0px");
        var answer = new Markdown();
        answer.getStyle().setOverflow(Style.Overflow.SCROLL);

        messageInput.addSubmitListener(e -> {
            chatClient.prompt()
                .user(e.getValue())
                .stream()
                .content()
                .subscribe(token -> {
                    ui.access(() -> answer.appendContent(token));
                });
        });

        messageInput.setWidthFull();
        add(messageInput);
        addAndExpand(answer);
    }
}
