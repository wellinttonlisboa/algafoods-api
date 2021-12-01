package br.com.algaworks.algafoods.handler;

import lombok.Getter;

@Getter
public enum RestExceptionType {
	
	ENTITY_NOT_FOUND("/entity-not-found", "Entity not found");
	
	public final String base_url = "https://www.algafoods.com.br";
	
	private String uri;
	private String title;
	
	RestExceptionType(String path, String title) {
		this.uri = base_url.concat(path);
		this.title = title;
	}
	
	
}
