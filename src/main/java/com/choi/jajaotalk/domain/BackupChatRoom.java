package com.choi.jajaotalk.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class BackupChatRoom {

    @Id @GeneratedValue
    @Column(name = "backup_chat_room_id")
    private Long id;

    @OneToMany(mappedBy = "backupChatRoom",cascade = CascadeType.ALL)
    private List<BackupChatLog> backupChatLogs = new ArrayList<>();

    private Long user_id;  //DB에 저장시 체크
    private String category;
    private String subject;
    private LocalDateTime deletedTime;
    private Timestamp createdTime;//DB에 시간저장시 type 체크

}
