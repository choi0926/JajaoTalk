package com.choi.jajaotalk.domain;

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

//    @OneToOne(mappedBy = "chatRoom", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private User user;

    @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<ChatLog> chatLogs = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Category category;

    private String subject;
    private int headCount;
    private LocalDateTime createdTime;

}
