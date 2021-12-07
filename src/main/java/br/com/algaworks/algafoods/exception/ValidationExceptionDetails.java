package br.com.algaworks.algafoods.exception;

import java.util.Map;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class ValidationExceptionDetails extends ExceptionDetails {
	
    private final Map<String, Object> fieldErrors;
    
}