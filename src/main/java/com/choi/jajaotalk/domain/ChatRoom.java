package com.choi.jajaotalk.domain;

import com.choi.jajaotalk.service.ChatLogService;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class ChatRoom {

    @Id @GeneratedValue
    @Column(name = "chat_room_id")
    private Long id;

    @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<ChatLog> chatLogs = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Category category;

    private String subject;
    private int headCount;
    private int currentHeadCount;
    private LocalDateTime createdTime;

    public static ChatRoom createChatRoom(String categoryCodeId,String subject, int headCount){
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.setCategory(Category.valueOf(categoryCodeId));
        chatRoom.setSubject(subject);
        chatRoom.setHeadCount(headCount);
        chatRoom.setCreatedTime(LocalDateTime.now());
        return chatRoom;
    }
    public void currentHeadCountPlus(){

        if (this.headCount>currentHeadCount){
        this.currentHeadCount++;
        } else {
            throw new IllegalStateException("This chat room is already full.");
        }
    }

    public void currentHeadCountMinus(){
        this.currentHeadCount--;
    }


}
