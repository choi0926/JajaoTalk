package com.choi.jajaotalk.api;

import com.choi.jajaotalk.domain.*;
import com.choi.jajaotalk.service.ChatRoomService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.toList;

@RestController
@RequiredArgsConstructor
public class ChatRoomApiController {

    private final ChatRoomService chatRoomService;

    @PostMapping("api/chat/new")
    public ChatRoomCreateRusult createChatRoom(@RequestBody CreateChatRoomDto createChatRoomDto) {

        ChatRoom newChatRoom = chatRoomService.createChatRoom(createChatRoomDto.getNickname(),createChatRoomDto.getCategoryCode(), createChatRoomDto.getSubject(), createChatRoomDto.getHeadCount());
        ChatRoomDto createChatRoom = new ChatRoomDto(newChatRoom.getId(), newChatRoom.getCategory().getValue(), newChatRoom.getSubject(), newChatRoom.getHeadCount(), newChatRoom.getCreatedTime());
        return new ChatRoomCreateRusult(true, 200, "You have successfully created a chat room.", createChatRoom);
    }

    @GetMapping("api/chat")
    public ChatRoomListResult chatRooms() {

        List<ChatRoom> findChatRooms = chatRoomService.findChatRooms();
        List<ChatRoomListDto> collect = findChatRooms.stream().distinct().map(chatRoom -> new ChatRoomListDto(chatRoom)).collect(toList());
        return new ChatRoomListResult(true, 200, "Successfully returns a list of chat rooms.", collect);
    }

    @GetMapping("api/chat/category")
    public ChatRoomListResult categories() {

        List<Category> al1 = new ArrayList<Category>(Arrays.asList(Category.values()));
        List<CategoryDto> collect = al1.stream().map(categoryCode -> new CategoryDto(categoryCode)).collect(Collectors.toList());
        return new ChatRoomListResult(true, 200, "Successfully return a list of categories.", collect);
    }

    @Data
    @AllArgsConstructor
    static class ChatRoomWithLog{

        private ChatRoomDto chatRoomDto;
        private ChatLogDto chatLogDto;
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
//        private List<ChatLogDto> chatLog;
        private String category;
        private String subject;
        private int headCount;
        private LocalDateTime createdTime;

        public ChatRoomListDto(ChatRoom chatRoom) {

            chatRoomId = chatRoom.getId();
//            chatLog = chatRoom.getChatLogs().stream().map(chatLog -> new ChatLogDto(chatLog)).collect(toList());
            chatLog = chatRoom.getChatLogs().stream().map(chatLog -> new ChatLogDto(chatLog)).max(Comparator.comparing(ChatLogDto::getChatLogTime)).get();
            category = chatRoom.getCategory().getKey();
            subject = chatRoom.getSubject();
            headCount = chatRoom.getHeadCount();
            createdTime = chatRoom.getCreatedTime();
        }
    }
    @Data
    static class ChatLogDto{

        private Long chatLogId;
        private String nickname;
        private String content;
        private LocalDateTime chatLogTime;

        public ChatLogDto(ChatLog chatLog) {

            chatLogId = chatLog.getId();
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
        private String category;
        private String subject;
        private int headCount;
        private LocalDateTime createTime;
    }

    @Data
    static class CategoryDto {

        private String categoryCode;
        private String categoryName;

        public CategoryDto(Category category) {

            categoryCode = category.getKey();
            categoryName = category.getValue();
        }
    }

    @Data
    @AllArgsConstructor
    static class ChatRoomCreateRusult<T> {

        private boolean success;
        private int status;
        private String message;
        private T data;
    }
}
