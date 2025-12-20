package br.hackathon.com.adapters.in.handlers;


import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import br.hackathon.com.domain.exceptions.AcessoNegadoException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(AcessoNegadoException.class)
	public ResponseEntity<Map<String, Object>> handleAcessoNegadoException(AcessoNegadoException ex, WebRequest request) {
		var body = new HashMap<String, Object>();
		
		body.put("timestamp", LocalDateTime.now());
		body.put("error", HttpStatus.UNAUTHORIZED.value());
		body.put("message", ex.getMessage());
		
		return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
	}
	
}
