package com.choi.jajaotalk.repository;

import com.choi.jajaotalk.domain.ChatRoom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ChatRoomRepository {

    private final EntityManager em;

    public void save(ChatRoom chatRoom) {
        em.persist(chatRoom);
    }

    public List<ChatRoom> findAll(int offset, int limit) {
        return em.createQuery("select cr from ChatRoom cr",ChatRoom.class).setFirstResult(offset).setMaxResults(limit).getResultList();
    }
}
