package com.choi.jajaotalk.service;

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

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChatLogService {

    private final ChatLogRepository chatLogRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final UserRepository userRepository;

    @Transactional
    public void createChatLog(Long chatRoomId, String nickname, String content,LocalDateTime chatLogTime){

        ChatRoom chatRoom = chatRoomRepository.findById(chatRoomId);
        User user = userRepository.findByNickname(nickname);

        ChatLog chatLog = ChatLog.createChatLog(chatRoom, user, content, chatLogTime);
        chatLogRepository.save(chatLog);
    }
}
