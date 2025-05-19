package com.example.scheduledevelop.exception;

public class WrongUserException extends RuntimeException{
    public WrongUserException() {

        super("다른 사용자의 일정입니다.");
    }
}
