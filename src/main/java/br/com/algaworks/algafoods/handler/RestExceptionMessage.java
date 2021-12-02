package br.com.algaworks.algafoods.handler;

import lombok.Getter;

@Getter
public enum RestExceptionMessage {
	
	MSG_GENERIC_ERROR("Contact administrator", "Contact administrator");
	
	private String details;
	private String developMessage;
	
	RestExceptionMessage(String details, String developMessage) {
		this.details = details;
		this.developMessage = developMessage;
	}
	
	
}
