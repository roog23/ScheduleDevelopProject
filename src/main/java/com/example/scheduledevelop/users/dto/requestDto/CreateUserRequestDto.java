package com.example.scheduledevelop.users.dto.requestDto;

import lombok.Getter;

@Getter
public class CreateUserRequestDto {
    private String username;
    private String email;
    private String password;
}
