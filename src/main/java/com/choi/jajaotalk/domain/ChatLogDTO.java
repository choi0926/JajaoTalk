package com.choi.jajaotalk.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ChatLogDTO {

    private MessageType type;
    private int chatRoomId;
    private String nickname;
    private String content;
    private LocalDateTime chatLogTime;

}
