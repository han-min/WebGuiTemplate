package org.hanmin.controller;

import lombok.extern.slf4j.Slf4j;
import org.hanmin.controller.websocket.ChatMessage;
import org.hanmin.controller.websocket.ChatOutputMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Controller
public class WebSocketResponseController {

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public ChatOutputMessage send(ChatMessage message) throws Exception {
        log("Got message " + message);
        String time = new SimpleDateFormat("HH:mm").format(new Date());
        ChatOutputMessage c = new ChatOutputMessage();
        c.setFrom(message.getFrom());
        c.setText(message.getText());
        c.setTime(time);
        return c;
    }

    private void log(String msg) {
        System.out.println((new Date()) + " " + msg);
        log.info(msg);
    }

}
