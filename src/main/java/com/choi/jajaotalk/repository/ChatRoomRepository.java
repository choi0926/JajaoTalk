package com.choi.jajaotalk.repository;

import com.choi.jajaotalk.domain.ChatRoom;
import static com.choi.jajaotalk.domain.QChatRoom.*;
import static com.choi.jajaotalk.domain.QChatLog.*;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
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

    public ChatRoom findById(Long id){
        return em.find(ChatRoom.class,id);
    }
    public void save(ChatRoom chatRoom) {
        em.persist(chatRoom);
    }

    public List<ChatRoom> findChatRoomsBySubject(String subject, int offset, int limit) {
        return query
                .selectFrom(chatRoom)
                .where(subjectLike(subject))
                .offset(offset)
                .limit(limit)
                .fetch();
    }

    private BooleanExpression subjectLike(String subject) {
        if (!StringUtils.hasText(subject)) {
            return null;
        }
        return chatRoom.subject.contains(subject);
    }

    public ChatRoom findOneChatRoom(Long id){
        return query
                .selectFrom(chatRoom)
                .where(chatRoom.id.eq(id))
                .fetchOne();
    }

    public void removeChatRoom(ChatRoom chatRoom){
        em.remove(chatRoom);
    }

//    public void removeChatRoomNotExistChatLog() {
////       return query.delete(chatRoom).where(chatLog.chatLogTime.eq());
//    }
}
