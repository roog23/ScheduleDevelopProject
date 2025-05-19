package com.example.scheduledevelop.exception;

import com.example.scheduledevelop.exception.dto.ErrorResponseDto;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleUserNotFound(UserNotFoundException e) {
        ErrorResponseDto response = new ErrorResponseDto(
                HttpStatus.NOT_FOUND.toString(),
                e.getMessage()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(ScheduleNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleScheduleNotFound(ScheduleNotFoundException e) {
        ErrorResponseDto response = new ErrorResponseDto(
                HttpStatus.NOT_FOUND.toString(),
                e.getMessage()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(WrongUserException.class)
    public ResponseEntity<ErrorResponseDto> handleWrongUser(WrongUserException e) {
        ErrorResponseDto response = new ErrorResponseDto(
                HttpStatus.UNAUTHORIZED.toString(),
                e.getMessage()
        );
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    @ExceptionHandler(WrongPasswordException.class)
    public ResponseEntity<ErrorResponseDto> handleWrongPassword(WrongPasswordException e) {
        ErrorResponseDto response = new ErrorResponseDto(
                HttpStatus.UNAUTHORIZED.toString(),
                e.getMessage()
        );
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> handleValid(MethodArgumentNotValidException e) {
        List<String> validMessage = e.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList();
        ErrorResponseDto response = new ErrorResponseDto(
                HttpStatus.BAD_REQUEST.toString(),
                validMessage.toString() // List에 담긴 오류메시지를 모두 출력해줍니다.
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

}
