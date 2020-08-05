//package com.choi.jajaotalk.config;
//
//import com.choi.jajaotalk.domain.ChatLogDTO;
//import com.choi.jajaotalk.domain.ChatRoom;
//import com.choi.jajaotalk.domain.MessageType;
//import com.choi.jajaotalk.service.ChatLogService;
//import com.choi.jajaotalk.service.ChatRoomService;
//import com.choi.jajaotalk.service.UserService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.Data;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Component;
//import org.springframework.web.socket.CloseStatus;
//import org.springframework.web.socket.TextMessage;
//import org.springframework.web.socket.WebSocketSession;
//import org.springframework.web.socket.handler.TextWebSocketHandler;
//
//import java.io.IOException;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
//@Component
//@RequiredArgsConstructor
//public class MySocketHandler extends TextWebSocketHandler {
//
//    private final ObjectMapper objectMapper;
//    private final ChatRoomService chatRoomService;
//    private final UserService userService;
//    private final ChatLogService chatLogService;
//    private List<WebSocketSession> sessionList = new ArrayList<WebSocketSession>();
//
//    @Override
//    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
//        //메시지 타입 Enter
//        sessionList.add(session);
//        System.out.println("연결됨 : " + session);
//    }
//
//    // 클라이언트가 서버로 메시지를 전송했을 때 실행되는 메서드
//    @Override
//    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
//        System.out.println(session.getId() + " : " + message.getPayload());
//        ChatLogDTO chatMessage = objectMapper.readValue(message.getPayload(), ChatLogDTO.class);
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
//
//    }
//
//    // 클라이언트와 연결을 끊었을 때 실행되는 메소드
//    @Override
//    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
//        //메시지 타입 EXIT
//        sessionList.remove(session);
//        System.out.println("연결 끊김 : " + session.getId());
//    }
//
//}
