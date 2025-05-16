package com.example.scheduledevelop.users.dto.requestDto;

import lombok.Getter;

@Getter
public class UpdateUserRequestDto {
    private String username;
    private String password;
    private String email;
}
