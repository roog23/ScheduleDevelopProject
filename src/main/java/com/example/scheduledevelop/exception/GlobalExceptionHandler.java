package com.example.scheduledevelop.exception;

import com.example.scheduledevelop.exception.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ScheduleNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleScheduleNotFound(ScheduleNotFoundException e) {
        ErrorResponseDto response = new ErrorResponseDto(
                HttpStatus.NOT_FOUND.toString(),
                e.getMessage()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(WrongPasswordException.class)
    public ResponseEntity<ErrorResponseDto> handleWrongPassword(WrongPasswordException e) {
        ErrorResponseDto response = new ErrorResponseDto(
                HttpStatus.UNAUTHORIZED.toString(),
                e.getMessage()
        );
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    @ExceptionHandler(UserNotFountException.class)
    public ResponseEntity<ErrorResponseDto> handleUserNotFound(UserNotFountException e) {
        ErrorResponseDto response = new ErrorResponseDto(
                HttpStatus.NOT_FOUND.toString(),
                e.getMessage()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
