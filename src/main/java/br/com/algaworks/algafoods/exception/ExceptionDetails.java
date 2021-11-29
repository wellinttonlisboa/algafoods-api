package br.com.algaworks.algafoods.exception;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class ExceptionDetails {
    protected String title;
    protected int status;
    protected String details;
    protected String developerMessage;
    @JsonInclude(Include.NON_NULL)
    protected String cause;
    protected LocalDateTime timestamp;
}
