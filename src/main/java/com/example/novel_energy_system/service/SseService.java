package com.example.novel_energy_system.service;

import com.example.novel_energy_system.common.SseMessage;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface SseService {

    SseEmitter connect(String clientId);

    void activeSendMessage(SseMessage sseMessage);

    void sendMessage();
}
