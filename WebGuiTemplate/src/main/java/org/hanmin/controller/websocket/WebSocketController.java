package org.hanmin.controller.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * Example from
 *   <a href="https://www.baeldung.com/websockets-spring">Example 1</a>
 * and
 *   <a href="https://docs.spring.io/spring-framework/reference/web/websocket.html">Example 2</a>
 *
 */

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketController implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/chat").withSockJS();
    }


}
