package com.malang.webservice.mobile.config;

import com.malang.webservice.mobile.handler.SocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import java.util.concurrent.Executor;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer{

    @Autowired
    SocketHandler socketHandler;

    @Bean
    public TaskScheduler taskScheduler() {
        return new ConcurrentTaskScheduler();
    }

    // Of course , you can define the Executor too
    @Bean
    public Executor taskExecutor() {
        return new SimpleAsyncTaskExecutor();
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(socketHandler, "/chatting");
    }
}
