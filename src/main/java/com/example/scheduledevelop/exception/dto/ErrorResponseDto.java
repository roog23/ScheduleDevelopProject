package com.example.scheduledevelop.exception.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResponseDto {
    //오류 상태
    private String status;
    //오류 메시지
    private String message;
}
