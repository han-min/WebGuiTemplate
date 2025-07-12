package org.hanmin.controller.websocket;

import lombok.Data;

@Data
public class ChatMessage {

    private String from;
    private String text;

}
