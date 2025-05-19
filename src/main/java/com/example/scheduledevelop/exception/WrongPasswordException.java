package com.example.scheduledevelop.exception;

public class WrongPasswordException extends RuntimeException {
    public WrongPasswordException() {

        super("비밀번호가 틀렸습니다.");
    }
}
