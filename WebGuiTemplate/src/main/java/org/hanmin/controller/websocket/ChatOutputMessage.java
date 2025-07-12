package org.hanmin.controller.websocket;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ChatOutputMessage extends ChatMessage {

    private String time;

}
