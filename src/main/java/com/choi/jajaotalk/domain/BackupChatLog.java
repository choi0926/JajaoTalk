package com.choi.jajaotalk.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class BackupChatLog {

    @Id @GeneratedValue
    @Column(name = "backup_chat_log_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "backup_chat_room_id")
    private BackupChatRoom backupChatRoom;

    @Lob
    private String content;
    private LocalDateTime chatLogTime;

}

