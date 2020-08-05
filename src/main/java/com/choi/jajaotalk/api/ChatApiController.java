package com.choi.jajaotalk.api;

import com.choi.jajaotalk.domain.MessageType;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class ChatApiController {


//    @MessageMapping("/chat/enter")
//    @SendTo("/topic/chatRoomId")
//    public ChatLogDTO sendMessage(ChatLogDTO chatLogDTO){
//
//    }

    @MessageMapping("/chat/send")
    @SendTo("/topic/chatRoomId")
    public Message sendMessage( Message message){

        return message;
    }

//    @MessageMapping("/chat/leave")
//    @SendTo("/topic/chatRoomId")
//    public ChatLogDTO sendMessage(ChatLogDTO chatLogDTO){
//
//    }

    @Data
   static class Message {

        private MessageType type;
        private Long chatRoomId;
        private String nickname;
        private String content;
        private LocalDateTime chatLogTime;

    }
}
