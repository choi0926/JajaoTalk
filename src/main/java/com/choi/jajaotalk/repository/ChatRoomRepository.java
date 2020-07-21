package com.choi.jajaotalk.repository;

import com.choi.jajaotalk.domain.ChatRoom;

import static com.choi.jajaotalk.domain.QChatRoom.*;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class ChatRoomRepository {

    private final EntityManager em;
    private final JPAQueryFactory query;

    public ChatRoomRepository(EntityManager em) {
        this.em = em;
        this.query = new JPAQueryFactory(em);
    }

    public void save(ChatRoom chatRoom) {
        em.persist(chatRoom);
    }

    public List<ChatRoom> findAll(int offset, int limit) {
        return em.createQuery("select cr from ChatRoom cr", ChatRoom.class).setFirstResult(offset).setMaxResults(limit).getResultList();
    }

    public List<ChatRoom> findBySubject(String subject, int offset, int limit) {
        return query.selectFrom(chatRoom)
                .where(subjectLike(subject))
                .offset(offset)
                .limit(limit)
                .fetch();
    }

    private BooleanExpression subjectLike(String subject) {
        if (!StringUtils.hasText(subject)) {
            return null;
        }
        return chatRoom.subject.like(subject);
    }
}
