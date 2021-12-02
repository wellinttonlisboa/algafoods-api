package br.com.algaworks.algafoods.handler;

import lombok.Getter;

@Getter
public enum RestExceptionType {
	
	ENTITY_NOT_FOUND("/entity-not-found", "Entity not found", "The entity was not found in the database");
	
	public final String base_url = "https://www.algafoods.com.br";
	
	private String uri;
	private String title;
	private String details;
	
	RestExceptionType(String path, String title, String details) {
		this.uri = base_url.concat(path);
		this.title = title;
		this.details = details;
	}
	
	
}
