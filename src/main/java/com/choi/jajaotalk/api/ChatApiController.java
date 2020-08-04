//package com.choi.jajaotalk.api;
//
//import com.choi.jajaotalk.config.MySocketHandler;
//import com.choi.jajaotalk.domain.ChatLog;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.handler.annotation.SendTo;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.socket.WebSocketSession;
//
//@RestController
//public class ChatApiController {
//
//        @Autowired
//        private MySocketHandler webSocketHandler;
//
//        @GetMapping("/")
//        public String index() {
//            return "index";
//        }
//
//        @GetMapping("/send")
//        @ResponseBody
//        public String send(String message) {
//            webSocketHandler.send(message);
//            return message;
//        }
//}
