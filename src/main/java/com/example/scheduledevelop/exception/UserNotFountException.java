package com.example.scheduledevelop.exception;

public class UserNotFountException extends RuntimeException {
    public UserNotFountException() {

        super("유저를 찾을 수 없습니다.");
    }
}
