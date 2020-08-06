package com.choi.jajaotalk.repository;

import com.choi.jajaotalk.domain.ChatLog;
import com.choi.jajaotalk.domain.ChatRoom;
import static com.choi.jajaotalk.domain.QChatRoom.*;
import static com.choi.jajaotalk.domain.QChatLog.*;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
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

    public void removeChatRoomNotExistChatLog() {

        List<ChatLog> chatLogs = query.selectFrom(chatLog).where(chatLogTimeOneHourBeforeExist()).fetch();
        for (int i =0; i<chatLogs.size(); i++){
            Long id = chatLogs.get(i).getChatRoom().getId();
            query.delete(chatRoom).where(chatRoom.id.eq(id)).execute();
        }
    }

    private BooleanExpression chatLogTimeOneHourBeforeExist(){

        LocalDateTime now = LocalDateTime.now().minusSeconds(5);
        return chatLog.chatLogTime.before(now);
    }
}
