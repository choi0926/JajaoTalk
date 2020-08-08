package com.choi.jajaotalk.api;

import com.choi.jajaotalk.domain.ChatRoom;
import com.choi.jajaotalk.repository.ChatRoomRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
public class ChatApiController {
    private final ChatRoomRepository chatRoomRepository;

    @GetMapping("/")
    public String rooms(Model model){
        String subject = null;
        int offset = 0;
        int limit = 3;
        model.addAttribute("rooms",chatRoomRepository.findChatRoomsBySubject(subject,offset,limit));
        return "rooms";
    }

    @GetMapping("/rooms/{id}")
    public String room(@PathVariable Long id, Model model){
        ChatRoom room = chatRoomRepository.findById(id);
        model.addAttribute("room",room);
        return "room";
    }
}

