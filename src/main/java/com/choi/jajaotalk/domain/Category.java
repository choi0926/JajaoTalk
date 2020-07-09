package com.choi.jajaotalk.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Category {

    @Id @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    private String categoryName;

    @OneToOne(mappedBy = "category", fetch = FetchType.LAZY)
    private ChatRoom chatRoom;
}
