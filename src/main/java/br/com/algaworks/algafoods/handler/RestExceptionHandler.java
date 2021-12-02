package br.com.algaworks.algafoods.handler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.algaworks.algafoods.exception.BadRequestException;
import br.com.algaworks.algafoods.exception.BadRequestExceptionDetails;
import br.com.algaworks.algafoods.exception.ExceptionDetails;
import br.com.algaworks.algafoods.exception.ValidationExceptionDetails;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(BadRequestException.class)
    public ResponseEntity<BadRequestExceptionDetails> handleBadRequestException(BadRequestException 
    		badRequestException) {
        return new ResponseEntity<>(
                BadRequestExceptionDetails.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .title("Bad Request Exception, Check the Documentation")
                        .details(badRequestException.getMessage())
                        .developerMessage(badRequestException.getClass().getName())
//                        .cause(badRequestException.getCause() != null ? ExceptionUtils
//                        		.getRootCause(badRequestException.getCause()).getLocalizedMessage() : null)
                        .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<BadRequestExceptionDetails> handleEntityNotFoundException(EntityNotFoundException 
    		entityNotFoundException) {
        return new ResponseEntity<>(
                BadRequestExceptionDetails.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.NOT_FOUND.value())
                        .title("Not Found Exception, Check the Documentation")
                        .details(entityNotFoundException.getMessage())
                        .developerMessage(entityNotFoundException.getClass().getName())
//                        .cause(entityNotFoundException.getCause() != null ? ExceptionUtils
//                        		.getRootCause(entityNotFoundException.getCause()).getLocalizedMessage() : null)
                        .build(), HttpStatus.NOT_FOUND);
    }
    
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

        String fields = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(", "));
        String fieldsMessage = fieldErrors.stream().map(FieldError::getDefaultMessage)
        		.collect(Collectors.joining(", "));

        return new ResponseEntity<>(
                ValidationExceptionDetails.builder()
                        .timestamp(LocalDateTime.now())
                        .status(status.value())
                        .title("Bad Request Exception, Invalid Fields")
                        .details("Check the field(s) error")
                        .developerMessage(exception.getClass().getName())
                        .fields(fields)
                        .fieldsMessage(fieldsMessage)
                        .build(), HttpStatus.BAD_REQUEST);
        
//		return super.handleExceptionInternal(exception, body, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception exception, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
    	   	
		return super.handleExceptionInternal(exception, ExceptionDetails.builder()
				.title(Objects.isNull(body) ? status.getReasonPhrase() : Objects.toString(body))
				.status(status.value())
				.details(RestExceptionMessage.MSG_GENERIC_ERROR.getDetails())
				.timestamp(LocalDateTime.now()).build(), headers, status, request);
    }
    
}