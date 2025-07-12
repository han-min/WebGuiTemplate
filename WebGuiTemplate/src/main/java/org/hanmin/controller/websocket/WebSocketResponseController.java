package org.hanmin.controller.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Show teo methods of sending message back
 */
@Slf4j
@Controller
public class WebSocketResponseController {

    /*
     * BIG FAT SWITCH to enable this feature.
     */
    private static final boolean ENABLED = false;

    private static final String SUBSCRIPTION_TOPIC = "/topic/messages";
    private static final int BACKGROUND_MAX = 100;

    private final ScheduledExecutorService executorService
            = Executors.newSingleThreadScheduledExecutor();

    private final SimpMessagingTemplate template;

    @Autowired
    public WebSocketResponseController(SimpMessagingTemplate template) {
        this.template = template;
        if (ENABLED) {
            scheduleUpdate(0);
        }
    }

    /**
     * The 'MessageMapping' is like GetMapping RequestMapping to handle trigger being sent to this address
     * The 'SendTo' is an easy way to mark and return the message.
     *   IMPORTANT: 'SendTo' does not work without 'MessageMapping'. See sendUpdate() method
     */
    @MessageMapping("/app/chat")
    @SendTo(SUBSCRIPTION_TOPIC)
    public ChatOutputMessage responseToMessage(ChatMessage message) throws Exception {
        log("Got message " + message);
        String time = new SimpleDateFormat("HH:mm").format(new Date());
        ChatOutputMessage c = new ChatOutputMessage();
        c.setFrom(message.getFrom());
        c.setText(message.getText());
        c.setTime(time);
        return c;
    }

    /**
     * This sends update to the topic using Spring SimpleMessagingTemplate
     */
    public void sendUpdate(int count){
        try {
            /* example from
             * https://docs.spring.io/spring-framework/reference/web/websocket/stomp/handle-send.html
             */
            log("Running send for " + count);
            ChatOutputMessage c = new ChatOutputMessage();
            String time = new SimpleDateFormat("HH:mm:ss").format(new Date());
            c.setFrom("Background");
            c.setText("The time now is " + time + " for " + count);
            c.setTime(time);
            if (count < BACKGROUND_MAX) {
                scheduleUpdate(count);
            }
            template.convertAndSend(SUBSCRIPTION_TOPIC, c);
        } catch (Exception e) {
            log(e.getMessage());
            log.error("", e);
        }
    }

    private void scheduleUpdate(int count){
        final int nextCount = count + 1;
        executorService.schedule(() -> sendUpdate(nextCount), 5, TimeUnit.SECONDS);
    }

    private void log(String msg) {
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())
                + " [" + this.getClass().getSimpleName() + "] " + msg);
        log.info(msg);
    }

}
