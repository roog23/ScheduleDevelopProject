package com.example.scheduledevelop.exception;

import com.example.scheduledevelop.exception.dto.ErrorResponseDto;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

/**
 * 발생하는 예외를 처리하는 핸들러입니다.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    //유저를 찾지 못한 경우 NOT_FOUND 와 작성된 메시지를 응답합니다.
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleUserNotFound(UserNotFoundException e) {
        ErrorResponseDto response = new ErrorResponseDto(
                HttpStatus.NOT_FOUND.toString(),
                e.getMessage()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    //할일을 찾지 못한 경우 NOT_FOUND 와 작성된 메시지를 응답합니다.
    @ExceptionHandler(ScheduleNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleScheduleNotFound(ScheduleNotFoundException e) {
        ErrorResponseDto response = new ErrorResponseDto(
                HttpStatus.NOT_FOUND.toString(),
                e.getMessage()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    //댓글을 찾지 못한 경우 NOT_FOUND 와 작성된 메시지를 응답합니다.
    @ExceptionHandler(CommentNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleCommentNotFound(CommentNotFoundException e) {
        ErrorResponseDto response = new ErrorResponseDto(
                HttpStatus.NOT_FOUND.toString(),
                e.getMessage()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    //로그인한 유저와 작성한 유저가 다른 경우 UNAUTHORIZED 와 작성된 메시지를 응답합니다.
    @ExceptionHandler(WrongUserException.class)
    public ResponseEntity<ErrorResponseDto> handleWrongUser(WrongUserException e) {
        ErrorResponseDto response = new ErrorResponseDto(
                HttpStatus.UNAUTHORIZED.toString(),
                e.getMessage()
        );
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    //입력받은 비밀번호가 저장된 비밀번호와 다른 경우 UNAUTHORIZED 와 작성된 메시지를 응답합니다.
    @ExceptionHandler(WrongPasswordException.class)
    public ResponseEntity<ErrorResponseDto> handleWrongPassword(WrongPasswordException e) {
        ErrorResponseDto response = new ErrorResponseDto(
                HttpStatus.UNAUTHORIZED.toString(),
                e.getMessage()
        );
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    //Valid에 의해 발생한 오류의 경우 BAD_REQUEST 와 작성된 메시지를 응답합니다.
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
