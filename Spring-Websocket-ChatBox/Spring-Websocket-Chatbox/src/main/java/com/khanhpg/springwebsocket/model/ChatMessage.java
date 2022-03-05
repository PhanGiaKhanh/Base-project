package com.khanhpg.springwebsocket.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @author: KhanhPG
 * @since: 05/03/2022
 */
@Getter
@Setter
public class ChatMessage {
	private String content;
	private String sender;
	private MessageType messageType;

	public enum MessageType {
		CHAT, LEAVE, JOIN
	}
}
