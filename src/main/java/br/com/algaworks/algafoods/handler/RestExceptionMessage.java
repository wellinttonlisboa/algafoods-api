package br.com.algaworks.algafoods.handler;

import lombok.Getter;

@Getter
public enum RestExceptionMessage {
	
	MSG_GENERIC_ERROR("Contact administrator"),
	RECOURCE_NOT_FOUND("The resource %s you tried to access is non-existent");
	
	private String details;
	
	RestExceptionMessage(String details) {
		this.details = details;
	}
	
	
}
