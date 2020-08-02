package com.choi.jajaotalk.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class ChatLog {

    @Id @GeneratedValue
    @Column(name = "chat_log_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_room_id")
    private ChatRoom chatRoom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String content;

    private LocalDateTime chatLogTime;

    public static ChatLog createChatLog(ChatRoom chatRoom,User user, String content, LocalDateTime chatLogTime){

        ChatLog chatLog = new ChatLog();
        chatLog.setChatRoom(chatRoom);
        chatLog.setUser(user);
        chatLog.setContent(content);
        chatLog.setChatLogTime(chatLogTime);
        return  chatLog;
    }

    public static ChatLog createChatRoomChatLog(ChatRoom chatRoom,User user){

        ChatLog chatLog = new ChatLog();
        chatLog.setChatRoom(chatRoom);
        chatLog.setContent("채팅방이 생성되었습니다.");
        chatLog.setUser(user);
        chatLog.setChatLogTime(chatRoom.getCreatedTime());
        return  chatLog;
    }
}
