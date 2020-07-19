package com.choi.jajaotalk.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class ChatFile {

    @Id @GeneratedValue
    @Column(name = "chat_file_id")
    private Long id;

//    @OneToOne(mappedBy = "chatFile", fetch = FetchType.LAZY)
//    private ChatLog chatLog;

    private String fileUrl;
}
