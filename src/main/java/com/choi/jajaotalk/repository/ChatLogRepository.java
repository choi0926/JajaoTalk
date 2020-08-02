package com.choi.jajaotalk.repository;


import com.choi.jajaotalk.domain.ChatLog;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class ChatLogRepository {

    private final EntityManager em;

    public void save(ChatLog chatLog){
        em.persist(chatLog);
    }
}
