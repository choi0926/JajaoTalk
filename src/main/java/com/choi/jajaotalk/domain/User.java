package com.choi.jajaotalk.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Getter @Setter
@Table(name = "users")
public class User {

    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    private ChatRoom chatRoom;

    private String nickname;
    private String password;
}
