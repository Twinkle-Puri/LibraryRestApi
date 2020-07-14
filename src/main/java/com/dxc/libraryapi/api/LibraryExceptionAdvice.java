package com.dxc.libraryapi.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.dxc.libraryapi.exception.LibraryException;

@RestControllerAdvice
public class LibraryExceptionAdvice {
	@ExceptionHandler(LibraryException.class)
	public ResponseEntity<String> handleItemException(LibraryException exception){
		return new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
	}

}
