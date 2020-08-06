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

        System.out.println("데이터"+message);

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
//    ChatLogDTO chatMessage = objectMapper.readValue(message.getPayload(), ChatLogDTO.class);
//        chatMessage.setChatLogTime(LocalDateTime.now());
//
//        //입장 메시지 타입 ENTER
//        if(chatMessage.getType().equals(MessageType.ENTER)){
////            sessionList.add(session);
//
//            //채팅방 인원 + 1
//            ChatRoom chatRoom = chatRoomService.currentHeadCountPlus(new Long(chatMessage.getChatRoomId()));
//
//            //user chatroom 정보 추가
//            userService.updateUserChatRoom(chatMessage.getNickname(),chatRoom);
//            chatMessage.setContent(chatMessage.getNickname()+"님이 채팅방에 입장하셨습니다.");
//        }
//
//        //퇴장 메시지 타입 LEAVE
//        if(chatMessage.getType().equals(MessageType.LEAVE)){
////            sessionList.remove(session);
//
//            //채팅방 인원 - 1
//            ChatRoom chatRoom = chatRoomService.currentHeadCountMinus(new Long(chatMessage.getChatRoomId()));
//
//            //user chatroom 정보 제거
//            userService.updateUserChatRoom(chatMessage.getNickname(),null);
//            chatMessage.setContent(chatMessage.getNickname()+"님이 채팅방을 나가셨습니다.");
//
//            //채팅방 참가 인원 0 명
//            if(chatRoom.getCurrentHeadCount() == 0 ) {
//                System.out.println("LEAVE - 1");
//                chatRoomService.deleteChatRoom(new Long(chatMessage.getChatRoomId()));
//            }
//        }
//
//
//        ChatRoom chatRoom = chatRoomService.findById(new Long(chatMessage.getChatRoomId()));
//        System.out.println("test"+chatRoom);
//        for (WebSocketSession sess : sessionList) {
//            try {
//            sess.sendMessage(new TextMessage(objectMapper.writeValueAsString(chatMessage)));
//            }catch (IOException e){
//                e.printStackTrace();
//            }
//        }
//
//        if(chatRoom != null){
//        chatLogService.createChatLog(new Long(chatMessage.getChatRoomId()),chatMessage.getNickname(),chatMessage.getContent(),chatMessage.getChatLogTime(),chatMessage.getType());
//        }