package com.gregory.workshopmongo.resources.exception;


import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.gregory.workshopmongo.services.exception.ObjectNotFoundException;

@ControllerAdvice // informa que a classe vai tratar erros que acontecerem
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)//captura as excessões ObjectNotFoundException
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {

		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(System.currentTimeMillis(), status.value(), "Não encontrado", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
}