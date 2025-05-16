package com.example.scheduledevelop.users.dto.responseDto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
public class UserInfoResponseDto {
    private final Long id;
    private final String username;
    private final String email;
    private final LocalDate createAt;
    private final LocalDate updateAt;

}
