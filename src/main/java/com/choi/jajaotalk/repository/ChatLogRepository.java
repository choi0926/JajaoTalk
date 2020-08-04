package com.choi.jajaotalk.repository;


import com.choi.jajaotalk.domain.ChatLog;
import com.choi.jajaotalk.domain.ChatRoom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ChatLogRepository {

    private final EntityManager em;

    public void save(ChatLog chatLog){
        em.persist(chatLog);
    }

    public List<ChatLog> findByChatRoomId(ChatRoom chatRoom) {
        return em.createQuery(" select cl from ChatLog cl " +
                " join fetch cl.user u" +
                " where cl.chatRoom =: chatRoom", ChatLog.class).setParameter("chatRoom",chatRoom).getResultList();
    }

}
