package org.example.expert.domain.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class InvalidRequestException extends IllegalArgumentException {
	private final HttpStatus status;

	public InvalidRequestException(String message) {
		this(message, HttpStatus.BAD_REQUEST); // 기본은 400
	}

	public InvalidRequestException(String message, HttpStatus status) {
		super(message);
		this.status = status;
	}

}
