package com.khanhpg.websocket.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* @author: KhanhPG
* @since: 05/03/2022	
*/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageModel {
	private String message;
	private String fromLogin;
}
