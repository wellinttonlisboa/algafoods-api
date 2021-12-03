package br.com.algaworks.algafoods.handler;

import lombok.Getter;

@Getter
public enum RestExceptionType {
	
	ENTITY_NOT_FOUND("/entity-not-found", "Entity not found"),
	RECOURCE_NOT_FOUND("/recource-not-found", "Recource not found"),
	SYSTEM_ERROR("/system-error", "System error");
	
	public final String base_url = "https://www.algafoods.com.br";
	
	private String type;
	private String title;
	
	RestExceptionType(String type, String title) {
		this.type = base_url.concat(type);
		this.title = title;
	}
	
	
}
