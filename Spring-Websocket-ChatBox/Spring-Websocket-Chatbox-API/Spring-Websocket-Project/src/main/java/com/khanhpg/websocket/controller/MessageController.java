package com.khanhpg.websocket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

import com.khanhpg.websocket.model.MessageModel;
import com.khanhpg.websocket.storage.UserStorage;

import lombok.extern.slf4j.Slf4j;

/**
 * @author: KhanhPG
 * @since: 05/03/2022
 */
@Slf4j
@RestController
public class MessageController {
	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;
	
	@MessageMapping("/chat/{to}")
	public void sendMessage(@DestinationVariable String to, MessageModel message) {
		log.info("handling send message: " + message + " to: " + to);
		boolean isExists = UserStorage.getInstance().getUsers().contains(to);
		if (isExists) {
			simpMessagingTemplate.convertAndSend("/topic/messages/" + to, message);
		}
		
	}
}
