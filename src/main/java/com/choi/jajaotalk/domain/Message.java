package com.choi.jajaotalk.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Message {
    private MessageType type;
    private Long chatRoomId;
    private String nickname;
    private String content;
    private LocalDateTime chatLogTime;

}