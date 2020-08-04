package com.choi.jajaotalk.api;

import com.choi.jajaotalk.domain.*;
import com.choi.jajaotalk.repository.ChatRoomRepository;
import com.choi.jajaotalk.service.ChatLogService;
import com.choi.jajaotalk.service.ChatRoomService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@RestController
@RequiredArgsConstructor
public class ChatRoomApiController {

    private final ChatRoomService chatRoomService;
    private final ChatLogService chatLogService;

    @PostMapping("api/chatroom")
    public ChatRoomListResult createChatRoom(@RequestBody CreateChatRoomDto createChatRoomDto) {

        ChatRoom newChatRoom = chatRoomService.createChatRoom(createChatRoomDto.getNickname(), createChatRoomDto.getCategoryCode(), createChatRoomDto.getSubject(), createChatRoomDto.getHeadCount());
        ChatRoom chatRoom = chatRoomService.findOneChatRoom(newChatRoom.getId());
        ChatRoomDto createChatRoom = new ChatRoomDto(newChatRoom.getId(),chatRoom.getChatLogs().stream().map(chatLog -> new ChatLogDto(chatLog)).min(Comparator.comparing(ChatLogDto::getChatLogTime)).get(),newChatRoom.getCategory().getValue(), newChatRoom.getSubject(), newChatRoom.getHeadCount(), newChatRoom.getCreatedTime());
        return new ChatRoomListResult(true, 200, "You have successfully created a chat room.", createChatRoom);
    }

    @GetMapping("api/chatrooms")
    public ChatRoomListResult chatRooms(@RequestParam(value = "subject") String subject,
                                        @RequestParam(value = "offset", defaultValue = "0") int offset,
                                        @RequestParam(value = "limit", defaultValue = "10") int limit) {
        //1 시간 동안 로그 없는 채팅방 삭제

        List<ChatRoom> findChatRooms = chatRoomService.searchChatRooms(subject, offset, limit);
        List<ChatRoomListDto> collect = findChatRooms.stream().distinct().map(chatRoom -> new ChatRoomListDto(chatRoom)).collect(toList());
        return new ChatRoomListResult(true, 200, "Successfully returns a list of chat rooms.", collect);
    }

    @GetMapping("api/chatroom/categories")
    public ChatRoomListResult categories() {

        List<Category> al1 = new ArrayList<Category>(Arrays.asList(Category.values()));
        List<CategoryDto> collect = al1.stream().map(categoryCode -> new CategoryDto(categoryCode)).collect(Collectors.toList());
        return new ChatRoomListResult(true, 200, "Successfully return a list of categories.", collect);
    }

    @GetMapping("api/chatroom/{id}")
    public ChatRoomListResult chatRoomChatLogs(@PathVariable Long id){
        List<ChatLog> chatLogs = chatLogService.findChatRoomIdByChatLog(id);
        List<ChatRoomChatLogDto> collect = chatLogs.stream().map(chatLog -> new ChatRoomChatLogDto(chatLog)).collect(toList());
        return new ChatRoomListResult(true, 200, "You have successfully viewed the chat log.", collect);
    }

    @Data
    @AllArgsConstructor
    static class ChatRoomListResult<T> {

        private boolean success;
        private int status;
        private String message;
        private T data;
    }

    @Data
    static class ChatRoomListDto {

        private Long chatRoomId;
        private ChatLogDto chatLog;
        private String category;
        private String subject;
        private int headCount;
        private LocalDateTime createdTime;

        public ChatRoomListDto(ChatRoom chatRoom) {

            chatRoomId = chatRoom.getId();
            chatLog = chatRoom.getChatLogs().stream().map(chatLog -> new ChatLogDto(chatLog)).max(Comparator.comparing(ChatLogDto::getChatLogTime)).get();
            category = chatRoom.getCategory().getValue();
            subject = chatRoom.getSubject();
            headCount = chatRoom.getHeadCount();
            createdTime = chatRoom.getCreatedTime();
        }
    }

    @Data
    static class ChatLogDto {

        private Long chatLogId;
        private String content;
        private LocalDateTime chatLogTime;

        public ChatLogDto(ChatLog chatLog) {

            chatLogId = chatLog.getId();
            content = chatLog.getContent();
            chatLogTime = chatLog.getChatLogTime();
        }
    }

    @Data
    static class ChatRoomChatLogDto {

        private Long chatLogId;
        private MessageType type;
        private Long chatRoomId;
        private String nickname;
        private String content;
        private LocalDateTime chatLogTime;

        public ChatRoomChatLogDto(ChatLog chatLog) {
            chatLogId = chatLog.getId();
            type = chatLog.getType();
            chatRoomId = chatLog.getChatRoom().getId();
            nickname = chatLog.getUser().getNickname();
            content = chatLog.getContent();
            chatLogTime = chatLog.getChatLogTime();
        }
    }

    @Data
    @AllArgsConstructor
    static class CreateChatRoomDto {

        private String nickname;
        private String categoryCode;
        private String subject;
        private int headCount;
    }

    @Data
    @AllArgsConstructor
    static class ChatRoomDto {

        private Long chatRoomId;
        private ChatLogDto chatLog;
        private String category;
        private String subject;
        private int headCount;
        private LocalDateTime createTime;

    }

    @Data
    static class CategoryDto {

        private String categoryCode;
        private String category;

        public CategoryDto(Category categories) {

            categoryCode = categories.getKey();
            category = categories.getValue();
        }
    }
}
