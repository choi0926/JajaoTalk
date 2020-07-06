package com.choi.jajaotalk.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class ChatImage {

    @Id @GeneratedValue
    @Column(name = "chat_image_id")
    private Long id;

    @OneToOne(mappedBy = "chatImage", fetch = FetchType.LAZY)
    private ChatLog chatLog;

    private String imageUrl;

}
