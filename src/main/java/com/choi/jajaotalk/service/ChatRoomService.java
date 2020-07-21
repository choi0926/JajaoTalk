package com.choi.jajaotalk.service;

import com.choi.jajaotalk.domain.Category;
import com.choi.jajaotalk.domain.ChatLog;
import com.choi.jajaotalk.domain.ChatRoom;
import com.choi.jajaotalk.domain.User;
import com.choi.jajaotalk.repository.ChatLogRepository;
import com.choi.jajaotalk.repository.ChatRoomRepository;
import com.choi.jajaotalk.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;
    private final ChatLogRepository chatLogRepository;
    private final UserRepository userRepository;

    @Transactional
    public ChatRoom createChatRoom(String nickname, String categoryCodeId, String subject, int headCount) {

        User user = userRepository.findByNickname(nickname);

        ChatRoom chatRoom = new ChatRoom();
        chatRoom.setCategory(Category.valueOf(categoryCodeId));
        chatRoom.setSubject(subject);
        chatRoom.setHeadCount(headCount);
        chatRoom.setCreatedTime(LocalDateTime.now());
        chatRoomRepository.save(chatRoom);

        user.setChatRoom(chatRoom);
        userRepository.save(user);

        ChatLog chatLog = new ChatLog();
        chatLog.setContent("채팅방이 생성되었습니다.");
        chatLog.setUser(user);
        chatLog.setChatLogTime(chatRoom.getCreatedTime());
        chatLog.setChatRoom(chatRoom);
        chatLogRepository.save(chatLog);

        return chatRoom;
    }

    public List<ChatRoom> findChatRooms(int offset, int limit) {
        return chatRoomRepository.findAll(offset, limit);
    }

    public List<ChatRoom> findBySubject(String subject, int offset, int limit) {
        return chatRoomRepository.findBySubject(subject, offset, limit);
    }

}
