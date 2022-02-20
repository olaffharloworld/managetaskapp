package nus.team1.adproject.ppmtool.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import nus.team1.adproject.ppmtool.domain.Message;

@Controller
public class ChatController {

	@Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/message")
    @SendTo("/chatroom/public")
    private Message receivePublicMessage(@Payload Message message) {
        return message;
    }

    @MessageMapping("/private-message")
    public Message receivePrivateMessage(@Payload Message message) {
        simpMessagingTemplate.convertAndSendToUser(message.getReceiverName(), "/private", message); // /user/David/private
        return message;
    }
}
