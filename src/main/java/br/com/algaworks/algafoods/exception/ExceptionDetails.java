package br.com.algaworks.algafoods.exception;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@Builder
@JsonInclude(Include.NON_NULL)
public class ExceptionDetails {
	
    protected String title;
    protected int status;
    protected String type;
    protected String details;
    protected String developerMessage;
    protected LocalDateTime timestamp;
    
}