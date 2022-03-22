package com.pjt.flowing.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class WSController {

    private final SimpMessageSendingOperations messagingTemplate;

    // 자기 자신의 아이디가 포함된 공간을 구독하여 계속 실시간으로 바라보게 만든다.
    // 따라서 내가 로그인 상태일 때 누군가가 나에게 초대 메세지를 보내면 바로 볼 수 있다.
//    @MessageMapping("/invite/{userId}")
//    public void invite(@DestinationVariable("userId") Long userId) {
//        messagingTemplate.convertAndSend("/sub/invite/" + userId, "alarm socket connection completed.");
//    }
}
